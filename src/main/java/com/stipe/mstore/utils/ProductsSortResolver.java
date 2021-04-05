package com.stipe.mstore.utils;

import com.stipe.mstore.helper.ProductsSort;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class ProductsSortResolver {

    public static org.springframework.data.domain.Sort resolveSortBy(ProductsSort sortBy) {
        List<org.springframework.data.domain.Sort.Order> sorts = new ArrayList<>();
        if (Objects.isNull(sortBy)) {
            sorts.add(org.springframework.data.domain.Sort.Order.desc("time"));
            return new com.stipe.mstore.utils.Sort(sorts);
        }
        switch (sortBy) {
            case Cheapest -> {
                sorts.add(org.springframework.data.domain.Sort.Order.asc("price"));
                return new com.stipe.mstore.utils.Sort(sorts);
            }
            case Expensive -> {
                sorts.add(org.springframework.data.domain.Sort.Order.desc("price"));
                return new com.stipe.mstore.utils.Sort(sorts);
            }
            case Latest -> {
                sorts.add(org.springframework.data.domain.Sort.Order.asc("time"));
                return new com.stipe.mstore.utils.Sort(sorts);
            }
            case Oldest -> {
                sorts.add(org.springframework.data.domain.Sort.Order.desc("time"));
                return new com.stipe.mstore.utils.Sort(sorts);
            }
        }
        return null;
    }
}