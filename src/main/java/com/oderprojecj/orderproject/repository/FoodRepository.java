package com.oderprojecj.orderproject.repository;

import com.oderprojecj.orderproject.model.Food;
import com.oderprojecj.orderproject.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
    boolean existsByRestaurantAndName(Restaurant restaurant, String name);
}
