package br.com.bancodigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bancodigital.model.Conta;
import br.com.bancodigital.model.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long>{

	List<Operacao> findByConta(Conta conta);

}
