package br.com.quickreader.userapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private LocalDateTime dataCadastro;
}
