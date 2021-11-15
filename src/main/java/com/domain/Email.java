package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(exclude = "author")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "email_id")
    int id;

    String email;

    @Version
    private int version;
}
