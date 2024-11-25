package com.ssg.wannavapibackend.service;

import com.ssg.wannavapibackend.dto.request.ReservationRequestDTO;
import com.ssg.wannavapibackend.dto.response.ReservationDTO;
import com.ssg.wannavapibackend.dto.response.ReservationPaymentResponseDTO;
import com.ssg.wannavapibackend.dto.response.ReservationSaveResponseDTO;
import com.ssg.wannavapibackend.dto.response.ReservationDateResponseDTO;

import java.util.List;

public interface ReservationService {
    List<ReservationDTO> getReservationList(Long UserId);

    ReservationDTO getReservation(Long ReservationId);

    ReservationPaymentResponseDTO getReservationPayment(Long ReservationId);

    ReservationSaveResponseDTO saveReservation(ReservationRequestDTO reservationRequestDTO);

    ReservationDateResponseDTO getReservationTime(ReservationRequestDTO reservationRequestDTO);
}
