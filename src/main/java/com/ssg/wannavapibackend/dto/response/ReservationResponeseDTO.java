package com.ssg.wannavapibackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponeseDTO {
    /**
     * 마이페이지 예약 조회 데이터
     */
    private Long id;

    private Long userId;

    private Long restaurantId;

    private Long paymentId;

    private Integer guestCount;

    private LocalDateTime scheduledAt;
}
