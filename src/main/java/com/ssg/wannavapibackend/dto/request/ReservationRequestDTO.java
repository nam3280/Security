package com.ssg.wannavapibackend.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationRequestDTO {

    private Long restaurantId;

    private Integer selectGuest;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate selectDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime selectTime;

    private Boolean isPanelty;
}
