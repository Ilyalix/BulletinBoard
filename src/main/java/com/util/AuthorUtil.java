package com.util;

import com.domain.Address;
import com.domain.Author;
import com.domain.Email;
import com.domain.Phone;

import java.util.List;

public class AuthorUtil {

    public static Author createAuthor() {

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


        Author author = Author.builder()
                .name("Masha")
                .email(gmail)
                .phones(List.of(number1, number2))
                .address(orsk)
                .build();
        number1.setAuthor(author);
        number2.setAuthor(author);

        return author;
    }
}
