package com.util;

import com.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AdvertisementUtil {


    public static Advertisement createAdvertisement() {
        Email gmail = Email.builder()
                .email("gmail.com")
                .build();

        Phone number1 = Phone.builder()
                .phone("088")
                .build();
        Phone number2 = Phone.builder()
                .phone("095")
                .build();

        Address orsk = Address.builder()
                .city("orsk")
                .build();


        Role role = Role.builder()
                .name(RoleName.ROLE_ADMIN)
                .build();

        Author author = Author.builder()
                .name("Masha")
                .age(25)
                .password("123456")
                .email(gmail)
                .phones(List.of(number1, number2))
                .address(orsk)
                .active(true)
                .roles(Set.of(role))
                .build();
        number1.setAuthor(author);
        number2.setAuthor(author);

        Category category = Category.builder()
                .name("House")
                .build();


        Advertisement advertisement = Advertisement.builder()
                .name("House")
                .dateOfPublic(LocalDate.of(2022, 5, 21))
                .text("sale")
                .price(BigDecimal.valueOf(1.5))
                .author(author)
                .category(category)
                .build();

        return advertisement;
    }
}
