package com.stipe.mstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stipe.mstore.enums.Color;
import com.stipe.mstore.enums.Currency;
import com.stipe.mstore.enums.Gender;
import com.stipe.mstore.enums.MainCategory;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product")
public class Product {

    @Id
    @JsonIgnore
    private String id;
    private String name;
    private String userId;
    private String description;
    private Boolean isNew;
    private byte[] image;
    private Float price;
    private Currency currency;

    //Q: replace with Brand model?
    private String brandId;
    private String brandName;

    //Q: replace with Size model?
    private String sizeId;
    private String sizeName;

    private Color color;
    private Gender gender;
    private MainCategory mainCategory;

    //Q: replace with Category model?
    private String categoryId;
    private String categoryName;

    private Long time;
    private Boolean isSold;
    private Long views;
}