package com.stipe.mstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "brand")
public class Brand {

    @Id
    @JsonIgnore
    private String id;
    private String name;
}