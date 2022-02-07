package br.com.bancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancodigital.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
