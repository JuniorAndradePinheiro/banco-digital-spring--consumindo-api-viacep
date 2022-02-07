package br.com.bancodigital.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Conta;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.repository.ContaRepository;
import br.com.bancodigital.service.ContaService;


@Component
public class ContaServiceImpl implements ContaService{
	
	@Autowired
	ContaRepository contaRepository;
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Conta salvar(Conta conta) {
		Long idCliente = conta.getCliente().getId();
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
//		Cliente cliente = clienteRepository.findById(idCliente).orElseGet(() -> {
//			return  new Cliente();
//		});
			conta.setCliente(cliente.get());
			contaRepository.save(conta);
			return conta;
		}
		
	

	@Override
	public void atualizar(Long id, Conta conta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Long id) {
		contaRepository.deleteById(id);
	}

}
