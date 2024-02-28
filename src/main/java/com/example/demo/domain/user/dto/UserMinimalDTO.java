package com.example.demo.domain.user.dto;

import com.example.demo.core.generic.AbstractDTO;
import com.example.demo.domain.role.dto.RoleDTO;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserMinimalDTO extends AbstractDTO {

    @Email
    private String email;

    public UserMinimalDTO(UUID id, String firstName, String lastName, String email, Set<RoleDTO> roles) {
        super(id);
        this.email = email;
    }

}
