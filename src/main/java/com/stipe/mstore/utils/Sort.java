package com.stipe.mstore.utils;

import java.util.List;

public class Sort extends org.springframework.data.domain.Sort {

    public Sort(List<Order> orders) {
        super(orders);
    }
}