package com.cognizant.springlearn.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Country {

    @NotBlank(message = "Country code must not be blank")
    @Size(min = 2, max = 2, message = "Country code must be exactly 2 characters")
    private String code;

    @NotNull(message = "Country name must not be null")
    @NotBlank(message = "Country name must not be blank")
    private String name;

    @Min(value = 1, message = "Display order must be at least 1")
    @Max(value = 999, message = "Display order must be at most 999")
    private Integer displayOrder;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}
