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
	
	/*
	 * ---------------------------------------- Daqui para baixo são métodos referentes as operações e transações ----------------------------------------
	 */

		@Override
		public void sacar(double valor, Long id) {
			Optional<Conta> contaOp = contaRepository.findById(id);
				
			Conta conta = (Conta) contaOp.get();
			conta.setSaldo(conta.getSaldo() - valor);
			contaRepository.save(conta);
			
		}

		@Override
		public void depositar(double valor, Long id) {
			Optional<Conta> contaOp = contaRepository.findById(id);
			
			Conta conta = (Conta) contaOp.get();
			conta.setSaldo(conta.getSaldo() + valor);
			contaRepository.save(conta);
			
		}

		@Override
		public void transferir(double valor, Long id, Long idContaDestino) {
			Optional<Conta> contaOp = contaRepository.findById(id);
			
			Conta conta = (Conta) contaOp.get();
			conta.setSaldo(conta.getSaldo() - valor);
			
			depositar(valor, idContaDestino);
			
			contaRepository.save(conta);
			
		}

}
