package com.oderprojecj.orderproject.controller;

import com.oderprojecj.orderproject.dto.RestaurantRequestDto;
import com.oderprojecj.orderproject.model.Restaurant;
import com.oderprojecj.orderproject.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurantsList() {
        return restaurantService.getRestaurantsList();
    }

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.saveRestaurant(requestDto);
    }
}
