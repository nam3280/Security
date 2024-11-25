package com.ssg.wannavapibackend.repository;

import com.ssg.wannavapibackend.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
