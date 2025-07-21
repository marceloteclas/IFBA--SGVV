package Cliente;

public abstract class ClienteMapper {
    public abstract Cliente toEntity(ClienteDTO clienteDTO);
    public abstract ClienteDTO toDTO(Cliente cliente);
}
