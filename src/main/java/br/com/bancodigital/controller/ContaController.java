package br.com.bancodigital.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.dto.ContaDto;
import br.com.bancodigital.dto.OperacaoDto;
import br.com.bancodigital.model.Conta;
import br.com.bancodigital.model.Operacao;
import br.com.bancodigital.repository.ContaRepository;
import br.com.bancodigital.repository.OperacaoRepository;
import br.com.bancodigital.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
//	@GetMapping
//	public ResponseEntity<List<Conta>> Listar(){
//		return ResponseEntity.ok(contaRepository.findAll());
//	}


	@GetMapping
	public ResponseEntity<List<ContaDto>> Listar(){
		return ResponseEntity.ok(contaRepository.findAll()
				.stream()
				.map(this::toContaDto)
				.collect(Collectors.toList()));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> buscarId(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		if(conta.isPresent()) {
			return ResponseEntity.ok(toContaDto(conta.get()));
		}
		
		else if(conta.isEmpty()) {
			return ResponseEntity.badRequest().build(); 
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Conta abrirConta(@RequestBody Conta conta) {
	
		return contaService.salvar(conta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> exlcluirConta(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(conta.isPresent()) {
			contaService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * ---------------------------------------- Daqui para baixo são métodos referentes as operações e transações ----------------------------------------
	 */
	
	@PostMapping("/sacar")
	public ResponseEntity<Object> sacar(@RequestParam Long id, @RequestParam double valor){
		
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(conta.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		contaService.sacar(valor, id);
		return ResponseEntity.ok().body(String.format("Saldo Atual: R$ %.2f", conta.get().getSaldo()));
	}
	
	@PostMapping("/depositar")
	public ResponseEntity<Object> Depositar(@RequestParam Long id, @RequestParam double valor){
		
		Optional<Conta> conta = contaRepository.findById(id);
		
		if(!conta.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		else if(conta.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		contaService.depositar(valor, id);
		return ResponseEntity.ok().body(String.format("Saldo Atual: R$ %.2f", conta.get().getSaldo()));
	}
	
	@PostMapping("/transferir")
	public ResponseEntity<?> transferir(@RequestParam Long id, @RequestParam double valor, @RequestParam Long idContaDestino){
		Optional<Conta> conta = contaRepository.findById(id);
		Optional<Conta> contaDest = contaRepository.findById(idContaDestino);
		
		if(conta.isPresent() && contaDest.isPresent()) {
			contaService.transferir(valor, id, idContaDestino);
			return ResponseEntity.ok().body(String.format("Saldo Atual: R$ %.2f", conta.get().getSaldo()));
		}
		
		else if(conta.isPresent() && !contaDest.isPresent()) {
			return ResponseEntity.badRequest().body(String.format("A conta com o número %d não foi encontrada", contaDest));
		}
		
		else if(!conta.isPresent() && !contaDest.isPresent()){
			return ResponseEntity.badRequest().body(String.format("A conta com o número %d não foi encontrada", conta));
		}
		
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@GetMapping("/extrato/{idConta}")
//	public ResponseEntity<List<Operacao>> extrato(@PathVariable Long idConta){
//		Optional<Conta> contaOp = contaRepository.findById(idConta);
//		Conta conta = contaOp.get();
//		return ResponseEntity.ok().body(operacaoRepository.findByConta(conta));
//	}
	

	@GetMapping("/extrato/{idConta}")
	public ResponseEntity<List<OperacaoDto>> extrato(@PathVariable Long idConta){
		Optional<Conta> contaOp = contaRepository.findById(idConta);
		Conta conta = contaOp.get();
		return ResponseEntity.ok().body(operacaoRepository.findByConta(conta)
				.stream()
				.map(this::toListOperacaoDto)
				.collect(Collectors.toList()));
	}
	
	private ContaDto toContaDto(Conta conta) {
		return modelMapper.map(conta, ContaDto.class);
	}
	
	private OperacaoDto toListOperacaoDto(Operacao operacao) {
		return  modelMapper.map(operacao, OperacaoDto.class);
	}
}
