package org.example.jobsrestfulapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "compony")
public class Compony {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String desc;
    private String city;
    private String url;
    @Field("image")
    private String image;
}
