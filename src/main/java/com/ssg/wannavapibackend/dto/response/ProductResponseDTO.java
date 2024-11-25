package com.ssg.wannavapibackend.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private long id;
    private String name;
    private List<String> image;
    private double sellingPrice;
    private int discountRate;
    private double finalPrice;
}