package com.stipe.mstore.utils;

import com.stipe.mstore.helper.ProductsFilter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Objects;

public final class ProductsFilterResolver {

    public static Query resolveProductsFilter(ProductsFilter filter) {

        Query query = new Query();
        if (Objects.nonNull(filter.getGender())) {
            var criteria = Criteria.where("gender").is(filter.getGender());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getUserId())) {
            var criteria = Criteria.where("userId").is(filter.getUserId());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getMainCategory())) {
            var criteria = Criteria.where("mainCategory").is(filter.getMainCategory());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getCategoryId()) && !filter.getCategoryId().equals("")) {
            var criteria = Criteria.where("categoryId").is(filter.getCategoryId());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getIsNew())) {
            var criteria = Criteria.where("isNew").is(filter.getIsNew());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getBrandNames()) && !filter.getBrandNames().isEmpty()) {
            var criteria = Criteria.where("brandName").in(filter.getBrandNames());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getColors()) && !filter.getColors().isEmpty()) {
            var criteria = Criteria.where("color").in(filter.getColors());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getSizeNames()) && !filter.getSizeNames().isEmpty()) {
            var criteria = Criteria.where("sizeName").in(filter.getSizeNames());
            query.addCriteria(criteria);
        }
        if (Objects.nonNull(filter.getStartPrice()) && Objects.nonNull(filter.getEndPrice())) {
            var criteria1 = Criteria.where("price").gte(filter.getStartPrice());
            var criteria2 = Criteria.where("price").lte(filter.getEndPrice());
            var criteria = new Criteria().andOperator(criteria1, criteria2);
            query.addCriteria(criteria);
        }
        return query;
    }
}