package org.example.jobsrestfulapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private String name;
}
