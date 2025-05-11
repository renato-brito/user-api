package br.com.quickreader.userapi.service;

import br.com.quickreader.userapi.dto.UserDTO;
import br.com.quickreader.userapi.model.User;
import br.com.quickreader.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::convert)
                .toList();
    }

    public UserDTO findByID(long id) {
        return userRepository.findById(id)
                .map(UserDTO::convert)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UserDTO findByCPF(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserDTO.convert(user);
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convertToUser(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (user != null) {
            userRepository.delete(user);
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String nome) {
        return userRepository.queryByNomeLike(nome).stream()
                .map(UserDTO::convert)
                .toList();
    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(userDTO.getNome());
        user.setCpf(userDTO.getCpf());
        user.setEndereco(userDTO.getEndereco());
        user.setEmail(userDTO.getEmail());
        user.setTelefone(userDTO.getTelefone());
        return UserDTO.convert(userRepository.save(user));
    }

    public Page<UserDTO> getAllPage(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserDTO::convert);
    }
}
