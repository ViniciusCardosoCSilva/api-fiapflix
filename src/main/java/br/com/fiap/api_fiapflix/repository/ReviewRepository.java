package br.com.fiap.api_fiapflix.repository;

import br.com.fiap.api_fiapflix.model.Filme;
import br.com.fiap.api_fiapflix.model.Review;
import br.com.fiap.api_fiapflix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

   @Query("SELECT obj " +
          "FROM Review obj " +
           "WHERE: filme IS NULL OR obj.filme = :filme "
   )
    List<Review> findByFilme(Filme filme);

   @Query("SELECT obj " +
          "FROM Review obj " +
          "WHERE: user IS NULL OR obj.user = :user")
   List<Review> findByUser(User user);

}
