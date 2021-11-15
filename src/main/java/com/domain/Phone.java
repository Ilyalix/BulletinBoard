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
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    int id;

    String phone;

    @ManyToOne
    @JoinColumn(name = "FK_Phone_Author")
    Author author;

    @Version
    private int version;
}
