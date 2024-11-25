package com.ssg.wannavapibackend.dto.response;

import com.ssg.wannavapibackend.domain.Payment;
import com.ssg.wannavapibackend.domain.Restaurant;
import com.ssg.wannavapibackend.domain.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    /**
     * 마이페이지 예약 조회 데이터
     */
    private Long id;
    private User user;
    private Restaurant restaurant;
    private Payment payment;
    private Integer guestCount;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
}
