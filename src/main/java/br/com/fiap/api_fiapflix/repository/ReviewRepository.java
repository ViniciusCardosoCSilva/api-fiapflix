package br.com.fiap.api_fiapflix.repository;

import br.com.fiap.api_fiapflix.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
