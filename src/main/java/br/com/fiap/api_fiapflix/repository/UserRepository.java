package br.com.fiap.api_fiapflix.repository;

import br.com.fiap.api_fiapflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
