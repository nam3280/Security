package com.ssg.wannavapibackend.controller.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssg.wannavapibackend.dto.response.KakaoResponseDTO;
import com.ssg.wannavapibackend.security.util.JWTUtil;
import com.ssg.wannavapibackend.service.KakaoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    @GetMapping("/")
    public String showRestaurantPage(Model model) {
        model.addAttribute("restaurantId",1);
        return "restaurant/reservation2";
    }
}
