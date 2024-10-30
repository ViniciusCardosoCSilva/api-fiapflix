package br.com.fiap.api_fiapflix.dto;

import br.com.fiap.api_fiapflix.model.Filme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class FilmeDTO {

    private Long id;

    private String titulo;
    private Integer ano;
    private GeneroDTO genero;

    public FilmeDTO(Filme entity) {

        id = entity.getId();
        titulo = entity.getTitulo();
        ano = entity.getAno();
        //aninhando DTO's
        genero = new GeneroDTO(entity.getGenero());
    }
}
