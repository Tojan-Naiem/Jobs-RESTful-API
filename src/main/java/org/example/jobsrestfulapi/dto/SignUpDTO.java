package org.example.jobsrestfulapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.jobsrestfulapi.model.Role;

import java.util.Set;

@Getter
@Setter
@Data
public class SignUpDTO {
    private String username;
    private String email;
    private String password;
}
