package com.ssg.wannavapibackend.repository;

import com.ssg.wannavapibackend.domain.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

}
