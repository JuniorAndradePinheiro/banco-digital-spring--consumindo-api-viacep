package br.com.bancodigital.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.bancodigital.service.exception.ElementNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.dto.ClienteDto;
import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ModelMapper modelMapper; //devido ao fato de ser somente uma biblioteca não é possivel injeta-la, por isso foi criada uma classe de configuração para criar um bean que o o spring gerencie 
	
//	@GetMapping()
//	public List<Cliente> Listar() {
//		return clienteService.buscarTodos();
//	}
	
	@GetMapping()
	public List<ClienteDto> Listar() {
		return clienteService.buscarTodos()
				.stream()
				.map(this::toListClienteDto)
				.collect(Collectors.toList());
	}	
	
	
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Cliente> buscarId(@PathVariable Long id){
//		Cliente cliente = clienteService.buscarPorId(id);
//		return ResponseEntity.ok(cliente);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarId(@PathVariable Long id){
		try{
			Cliente cliente = clienteService.buscarPorId(id);
			return ResponseEntity.ok(toListClienteDto(cliente));
		}catch (ElementNotFoundException e){
			e.getMessage();
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente){
		return clienteService.salvar(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional <Cliente> cliente = clienteRepository.findById(id);	
		
		if(cliente.isPresent()) {
			clienteService.deletar(id);
			return ResponseEntity.noContent().build();
		}
		
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private ClienteDto toListClienteDto(Cliente cliente){
		return modelMapper.map(cliente, ClienteDto.class);
	}
}
