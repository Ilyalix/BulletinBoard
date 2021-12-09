package com.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@AllArgsConstructor
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class MatchingAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_ad_id")
    int id;

    String title;

    BigDecimal priceTo;

    BigDecimal priceFrom;

    @OneToOne
    @JoinColumn(name = "FK_Mad_Category")
    Category category;

    @OneToOne
    @JoinColumn(name = "FK_Mad_Author")
    Author author;

    @Version
    private int version;
}
