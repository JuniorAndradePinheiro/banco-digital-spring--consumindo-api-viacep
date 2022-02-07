package br.com.bancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bancodigital.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String>{

}
