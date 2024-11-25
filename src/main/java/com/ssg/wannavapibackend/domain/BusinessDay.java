package com.ssg.wannavapibackend.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Entity
@Getter @Setter(AccessLevel.PRIVATE)
public class BusinessDay {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private String dayOfWeek; //요일


  @DateTimeFormat(pattern = "hh:mm")
  @Column(name = "open_time")
  private LocalTime openTime; //영업 시작 시간

  @DateTimeFormat(pattern = "hh:mm")
  @Column(name = "close_time")
  private LocalTime closeTime; //종료 시간

  @DateTimeFormat(pattern = "hh:mm")
  @Column(name = "break_start_time")
  private LocalTime breakStartTime; //브레이크 댄스 타임 시작 시간

  @DateTimeFormat(pattern = "hh:mm")
  @Column(name = "break_end_time")
  private LocalTime breakEndTime; //브레이크 댄스 타임 종료 시간

  @DateTimeFormat(pattern = "hh:mm")
  @Column(name = "last_order")
  private LocalTime lastOrderTime; //라스트 오더 시간

    @Column(name = "is_close")
  private Boolean isClose; //문 닫는 날인지 , 이때는 전부 null 값임

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "restaurant_id") //restaurant_id
  private Restaurant restaurant;
}
