package br.com.fiap.api_fiapflix.dto;

import br.com.fiap.api_fiapflix.model.Review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReviewDTO {

    private Long id;
    @NotBlank(message = "Campo requerido")
    private String texto;
    @NotNull(message = "Campo requerido")
    private Long filmeId;

    private Long userId;

    public ReviewDTO(Review entity) {
        id = entity.getId();
        texto = entity.getTexto();
        filmeId = entity.getFilme().getId() ;
        userId = entity.getUser().getId();
    }
}
