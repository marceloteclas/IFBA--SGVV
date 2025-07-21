package Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteMapper = new clienteMapper();
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        if(cliente.getNome() == null || cliente.getEmail() == null || cliente.getTelefone() == null ||
           cliente.getCnh() == null || cliente.getCpf() == null || cliente.getEndereco() == null) {
            throw new IllegalArgumentException("Todos os campos do cliente devem ser preenchidos.");
        }
        return clienteRepository.save(cliente); 
    }

    @Override
    public List<Cliente> buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
        .stream()
        .map(clienteMapper::toDTO)
        .collect(Collectors.toList());
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarCliente(long id) {
        if(!clienteRepository.existsById(id)){
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public Endereco cadastrarEndereco(Endereco endereco) {
        return clienteRepository.save(endereco);
    }

    @Override
    public List<Endereco> buscarEnderecoPorCep(String cep) {
        return clienteRepository.findByCep(cep)
        .stream()
        .map(clienteMapper::toDTO)
        .collect(Collectors.toList());
    }
    @Override
    public Endereco atualizarEndereco(Endereco endereco) {
       Endereco entity = clienteRepository.findById(endereco.getId())
               .orElseThrow(() -> new IllegalArgumentException("Endereco não encontrado com o ID: " + endereco.getId()));
       entity.setLogradouro(endereco.getLogradouro());
       entity.setNumero(endereco.getNumero());
       entity.setComplemento(endereco.getComplemento());
       entity.setBairro(endereco.getBairro());
       entity.setCidade(endereco.getCidade());
       entity.setEstado(endereco.getEstado());
       entity.setCep(endereco.getCep());
       return clienteRepository.save(entity);
    }
}
