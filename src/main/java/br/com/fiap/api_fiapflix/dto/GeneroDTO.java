package br.com.fiap.api_fiapflix.dto;

import br.com.fiap.api_fiapflix.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class GeneroDTO {

    private Long id;
    private String nome;

    public GeneroDTO(Genero entity) {

        id = entity.getId();
        nome = entity.getNome();
    }
}
