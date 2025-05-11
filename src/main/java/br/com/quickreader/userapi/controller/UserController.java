package br.com.quickreader.userapi.controller;

import br.com.quickreader.userapi.dto.PageResponse;
import br.com.quickreader.userapi.dto.UserDTO;
import br.com.quickreader.userapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    public static final List<UserDTO> usuarios = new ArrayList<>();
//
//    @PostConstruct
//    public void initiateList() {
//        UserDTO user1 = new UserDTO();
//        user1.setNome("Nubia");
//        user1.setCpf("123");
//        user1.setEndereco("Rua 1");
//        user1.setEmail("nubia@gmail.com");
//        user1.setTelefone("12345-6789");
//        user1.setDataCadastro(LocalDateTime.now());
//
//        UserDTO user2 = new UserDTO();
//        user2.setNome("Carlos");
//        user2.setCpf("456");
//        user2.setEndereco("Rua 2");
//        user2.setEmail("carlos@hotmail.com");
//        user2.setTelefone("98765-4321");
//        user2.setDataCadastro(LocalDateTime.now());
//
//        UserDTO user3 = new UserDTO();
//        user3.setNome("Ana");
//        user3.setCpf("789");
//        user3.setEndereco("Rua 3");
//        user3.setEmail("ana@yahoo.com.br");
//        user3.setTelefone("54321-6789");
//        user3.setDataCadastro(LocalDateTime.now());
//
//        usuarios.add(user1);
//        usuarios.add(user2);
//        usuarios.add(user3);
//    }

    @GetMapping("/")
    public String getMensagem() {
        return "Hello World";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO getUsersFiltro(@PathVariable String cpf) {
        return userService.findByCPF(cpf);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return userService.findByID(id);
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(required = true) String nome) {
        return userService.queryByName(nome);
    }

    @PatchMapping("/{userId}")
    public UserDTO editUser(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
        return userService.editUser(userId, userDTO);
    }

    @GetMapping("/pageable")
    public PagedModel<EntityModel<UserDTO>> getUsersPage(Pageable pageable, PagedResourcesAssembler<UserDTO> assembler) {
        return userService.getAllPage(pageable, assembler);
    }

    // Modelo simples
    @GetMapping("/page")
    public PageResponse<UserDTO> getUsersPage(Pageable pageable) {
        Page<UserDTO> page = userService.getAllPage(pageable);

        return new PageResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst()
        );
    }
}
