package br.com.quickreader.userapi.repository;

import br.com.quickreader.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(String cpf);

    @Query("SELECT u FROM User u WHERE u.nome LIKE %:nome%")
    List<User> queryByNomeLike(String nome);
}
