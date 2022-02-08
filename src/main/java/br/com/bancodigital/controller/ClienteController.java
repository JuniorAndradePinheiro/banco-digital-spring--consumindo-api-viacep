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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping()
	public List<Cliente> Listar() {
		return clienteService.buscarTodos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarId(@PathVariable Long id){
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok(cliente);
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
}
