package com.oderprojecj.orderproject.repository;

import com.oderprojecj.orderproject.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
