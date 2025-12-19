package org.example.jobsrestfulapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LoginDTO {
    private String email;
    private String password;

}
