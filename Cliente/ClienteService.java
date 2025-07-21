package Cliente;

import java.util.List;

public interface ClienteService {

        Cliente cadastrarCliente(Cliente cliente);
        List<Cliente> buscarClientePorCpf(long cpf);
        Cliente atualizarCliente(Cliente cliente);
        void deletarCliente(long id);
        Endereco cadastrarEndereco(Endereco endereco);
        List<Endereco> buscarEnderecoPorCep(String cep);
        Endereco atualizarEndereco(Endereco endereco);
}