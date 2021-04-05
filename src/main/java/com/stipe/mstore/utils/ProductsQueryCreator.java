package com.stipe.mstore.utils;

import com.stipe.mstore.helper.ProductsFilter;
import org.springframework.data.mongodb.core.query.Query;

public class ProductsQueryCreator {

    public static Query createQuery(ProductsFilter filter) {
        Query query = ProductsFilterResolver.resolveProductsFilter(filter);
        query.with(ProductsSortResolver.resolveSortBy(filter.getSortBy()));
        return query;
    }
}