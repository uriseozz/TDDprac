package com.oderprojecj.orderproject.controller;

import com.oderprojecj.orderproject.dto.FoodRequestDto;
import com.oderprojecj.orderproject.model.Food;
import com.oderprojecj.orderproject.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDtoList) {
        foodService.registerFood(restaurantId, requestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFoodList(@PathVariable Long restaurantId) {
        return foodService.findFoodList(restaurantId);
    }
}
