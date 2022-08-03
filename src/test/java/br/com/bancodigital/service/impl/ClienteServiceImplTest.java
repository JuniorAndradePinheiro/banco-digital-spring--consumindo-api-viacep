package br.com.bancodigital.service.impl;

import br.com.bancodigital.model.Cliente;
import br.com.bancodigital.model.Endereco;
import br.com.bancodigital.repository.ClienteRepository;
import br.com.bancodigital.repository.EnderecoRepository;
import br.com.bancodigital.service.ViaCepService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteServiceImplTest {

    public static final String NOME = "João";
    public static final String TELEFONE = "13 98133-6701";
    public static final Endereco ENDERECO = new Endereco("11712-580", "ABC", "Casa", "Tupi", "Praia Grande", "SP", "2a5s4", "222", "013", "FFF9");
    public static final long ID = 1L;
    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository repository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ViaCepService viaCep;

    private Cliente cliente;
    private Optional<Cliente> clienteOptional;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startCliente();
    }

    @Test
    void buscarTodos() {
    }

    @Test
    void whenBuscarPorIdReturnClienteInstence() {
        when(repository.findById(anyLong())).thenReturn(clienteOptional);

        Cliente response = clienteService.buscarPorId(ID);

        assertEquals(Cliente.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(ENDERECO,response.getEndereco());
    }

    @Test
    void salvar() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }

    private void startCliente(){
        cliente = new Cliente(ID, NOME, TELEFONE, ENDERECO);
        clienteOptional = Optional.of(new Cliente(ID, NOME, TELEFONE, ENDERECO));
    }
}