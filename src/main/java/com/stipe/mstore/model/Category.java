package com.stipe.mstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stipe.mstore.enums.MainCategory;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "category")
public class Category {

    @Id
    @JsonIgnore
    private String id;
    private String name;
    private MainCategory mainCategory;
}