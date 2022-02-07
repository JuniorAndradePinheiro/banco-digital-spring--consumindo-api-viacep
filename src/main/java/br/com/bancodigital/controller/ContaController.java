package br.com.bancodigital.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.model.Conta;
import br.com.bancodigital.repository.ContaRepository;
import br.com.bancodigital.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	ContaService contaService;
	
	
	@GetMapping
	public ResponseEntity<List<Conta>> Listar(){
		return ResponseEntity.ok(contaRepository.findAll());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Conta> buscarId(@PathVariable Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		if(conta.isPresent()) {
			return ResponseEntity.ok(conta.get());
		}
		
		else if(conta.isEmpty()) {
			return (ResponseEntity<Conta>) ResponseEntity.badRequest(); 
		}
		else {
			return (ResponseEntity<Conta>) ResponseEntity.notFound();
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
			return (ResponseEntity<?>) ResponseEntity.noContent();
		}
		
		else {
			return (ResponseEntity<?>) ResponseEntity.notFound();
		}
	}
}
