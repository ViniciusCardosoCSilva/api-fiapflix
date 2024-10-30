package br.com.fiap.api_fiapflix.dto;

import br.com.fiap.api_fiapflix.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

    public UserDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        password = entity.getPassword();
    }
}
