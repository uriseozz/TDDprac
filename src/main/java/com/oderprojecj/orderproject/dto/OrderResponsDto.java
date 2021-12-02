package com.oderprojecj.orderproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponsDto {
    private String restaurantName;
    private List<FoodOrderResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;

}

