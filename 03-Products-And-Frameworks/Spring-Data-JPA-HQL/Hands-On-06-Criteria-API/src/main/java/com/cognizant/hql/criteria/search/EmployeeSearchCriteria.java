package com.cognizant.hql.criteria.search;

import java.math.BigDecimal;
import java.util.Optional;

public record EmployeeSearchCriteria(
        Optional<BigDecimal> minSalary,
        Optional<BigDecimal> maxSalary,
        Optional<String> departmentName,
        Optional<Boolean> permanent,
        Optional<String> nameContains,
        Optional<String> skillName) {

    public static EmployeeSearchCriteria amazonLike(String keyword) {
        return new EmployeeSearchCriteria(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(true),
                Optional.of(keyword),
                Optional.empty());
    }
}
