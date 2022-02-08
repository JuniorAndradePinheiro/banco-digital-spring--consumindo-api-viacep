package br.com.bancodigital.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Endereco;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.repository.EnderecoRepository;
import br.com.bancodigital.service.ClienteService;
import br.com.bancodigital.service.ViaCepService;

@Component
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ViaCepService viaCep;
	
	
	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get(); 
	}

	@Override
	public Cliente salvar(@RequestBody Cliente cliente) {
		return salvarClientecomCep(cliente);
	}


	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
			if(clienteBd.isPresent()) {
				clienteRepository.save(cliente);
			}
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
	
	private Cliente salvarClientecomCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
			Endereco novoEndereco = viaCep.buscarEndecoPorCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		return clienteRepository.save(cliente);
	}

}
