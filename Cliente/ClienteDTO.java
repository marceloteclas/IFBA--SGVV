package Cliente;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Data
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais de 100")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "Telefone deve ter 11 dígitos")
    private String telefone;

    @NotBlank(message = "CNH é obrigatória")
    @Pattern(regexp = "\\d{11}", message = "CNH deve ter 11 dígitos")
    private String cnh;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;


    private EnderecoDTO endereco;

}
