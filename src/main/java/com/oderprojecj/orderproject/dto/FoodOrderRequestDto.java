package com.oderprojecj.orderproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FoodOrderRequestDto {
    private Long id;
    private int quantity;

}
