package com.util;

import com.domain.*;

import java.util.List;

public class CategoryUtil {

    public static Category createCategory() {

        Category category = Category.builder()
                .name("House")
                .build();

        return category;
    }
}
