package com.stipe.mstore.helper;

import com.stipe.mstore.enums.Color;
import com.stipe.mstore.enums.Gender;
import com.stipe.mstore.enums.MainCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductsFilter {
    private Float startPrice;
    private Float endPrice;
    private Gender gender;
    private List<Color> colors;
    private List<String> sizeNames;
    private List<String> brandNames;
    private MainCategory mainCategory;
    private String categoryName;
    private String categoryId;
    private Boolean isNew;
    private Boolean isSold;
    private ProductsSort sortBy;
    private String userId;
}