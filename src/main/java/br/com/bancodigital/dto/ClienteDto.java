package br.com.bancodigital.dto;

public class ClienteDto {

	private Long id;
	private String nome;
	private String telefone;
	private  String enderecoCep;
	private  String enderecoLogradouro;
	private  String enderecoLocalidade;
	private  String enderecoUf;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEnderecoCep() {
		return enderecoCep;
	}
	public void setCepEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}
	public String getEnderecoLogradouro() {
		return enderecoLogradouro;
	}
	public void setEnderecoLogradouro(String enderecoLogradouro) {
		this.enderecoLogradouro = enderecoLogradouro;
	}
	public String getEnderecoLocalidade() {
		return enderecoLocalidade;
	}
	public void setEnderecoLocalidade(String enderecoLocalidade) {
		this.enderecoLocalidade = enderecoLocalidade;
	}
	public String getEnderecoUf() {
		return enderecoUf;
	}
	public void setEnderecoUf(String enderecoUf) {
		this.enderecoUf = enderecoUf;
	}
	public void setEnderecoCep(String enderecoCep) {
		this.enderecoCep = enderecoCep;
	}
	
	
}
