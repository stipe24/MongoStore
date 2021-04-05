package com.stipe.mstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "auth_user")
public class User {
    @Id
    @JsonIgnore
    private String id;
    private String username;
    private String email;
    private String password;
    private String description;
    private byte[] image;
    private List<String> roles;
}