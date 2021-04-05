package com.stipe.mstore.helper;

import com.stipe.mstore.enums.Currency;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductWish {
    private String id;
    private Boolean isNew;
    private Boolean isSold;
    private String name;
    private Float price;
    private Currency currency;
    private boolean inWishlist;
}