package br.com.fiap.api_fiapflix.repository;

import br.com.fiap.api_fiapflix.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
