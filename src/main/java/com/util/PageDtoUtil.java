package com.util;

import com.domain.Category;
import com.dto.PageDTO;

public class PageDtoUtil {

    public static PageDTO createPageDTO() {

        PageDTO pageDTO = PageDTO.builder()
                .page(0)
                .size(1)
                .build();

        return pageDTO;
    }
}
