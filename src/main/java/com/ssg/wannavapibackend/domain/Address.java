package com.ssg.wannavapibackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "land_lot_address")
    private String landLotAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "zip_code")
    private String zipCode;
}
