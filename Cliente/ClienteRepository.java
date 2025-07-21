package Cliente;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import javax.management.loading.ClassLoaderRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);
    List<Cliente> findByNome(String nome);
    
    List<Cliente> findByCpf(long cpf);
    List<Endereco> findByCep(String cep);
    Endereco save(Endereco endereco);
}
