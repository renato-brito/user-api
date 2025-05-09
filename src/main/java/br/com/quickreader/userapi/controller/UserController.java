package br.com.quickreader.userapi.controller;

import br.com.quickreader.userapi.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initiateList() {
        UserDTO user1 = new UserDTO();
        user1.setNome("Nubia");
        user1.setCpf("123");
        user1.setEndereco("Rua 1");
        user1.setTelefone("12345-6789");
        user1.setDataCadastro(LocalDateTime.now());

        UserDTO user2 = new UserDTO();
        user2.setNome("Carlos");
        user2.setCpf("456");
        user2.setEndereco("Rua 2");
        user2.setTelefone("98765-4321");
        user2.setDataCadastro(LocalDateTime.now());

        UserDTO user3 = new UserDTO();
        user3.setNome("Ana");
        user3.setCpf("789");
        user3.setEndereco("Rua 3");
        user3.setTelefone("54321-6789");
        user3.setDataCadastro(LocalDateTime.now());

        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
    }

    @GetMapping("/")
    public String getMensagem() {
        return "Hello World";
    }

    @GetMapping
    public List<UserDTO> getUsuarios() {
        return usuarios;
    }
}
