package br.com.bancodigital.service;

import org.springframework.stereotype.Service;

import br.com.bancodigital.model.Conta;

@Service
public interface ContaService {
	
	Conta salvar(Conta conta);
	void atualizar(Long id, Conta conta);
	void deletar(Long id);

	void sacar(double valor, Long id);
	void depositar(double valor, Long id);
	void transferir(double valor, Long id, Long idContaDestino);
}
