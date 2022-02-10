package com.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class PageDTO {
    int page;
    int size;

    public PageDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
