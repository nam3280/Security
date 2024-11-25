package com.ssg.wannavapibackend.controller.web;

import com.ssg.wannavapibackend.service.ReservationService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("restaurant/{restaurantId}")
    public String test2(@PathVariable("restaurantId") Long restaurantId, RedirectAttributes redirectAttributes) {
        log.info("여기 왔당");

        redirectAttributes.addFlashAttribute("restaurantId", restaurantId);

        return "redirect:/reservation/calendar";
    }

    @GetMapping("/reservation/calendar")
    public String reservation1(@ModelAttribute("restaurantId") Long restaurantId) {
        log.info(restaurantId);
        return "/reservation/calendar";
    }

    @GetMapping("/reservation")
    public String reservation() {
        log.info("예약 끝!");
        return "/payment/success";
    }
}
