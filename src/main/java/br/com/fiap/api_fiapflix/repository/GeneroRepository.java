package br.com.fiap.api_fiapflix.repository;

import br.com.fiap.api_fiapflix.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
