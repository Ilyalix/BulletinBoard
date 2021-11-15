package com.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "advertisement_id")
    int id;

    @NotNull
    @Size(max = 10)
    String name;

    LocalDate dateOfPublic;
    String text;
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "FK_Ad_Author")
    Author author;

    @ManyToOne
    @JoinColumn(name = "FK_Ad_Category")
    Category category;

    @Version
    private int version;
}
