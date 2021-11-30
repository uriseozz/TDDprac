package com.oderprojecj.orderproject.service;

import com.oderprojecj.orderproject.dto.RestaurantRequestDto;
import com.oderprojecj.orderproject.model.Restaurant;
import com.oderprojecj.orderproject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Transactional
    public Restaurant saveRestaurant(RestaurantRequestDto requestDto) {

        if (requestDto.getMinOrderPrice() < 1000 || requestDto.getMinOrderPrice() > 100000) {
            throw new IllegalArgumentException("최소주문금액은 1000~100000원 입니다.");
        }

        if (requestDto.getMinOrderPrice() % 100 != 0) {
            throw new IllegalArgumentException("100원 단위로 입력가능합니다.");
        }

        if (requestDto.getDeliveryFee() < 0 || requestDto.getDeliveryFee() > 10000 ) {
            throw new IllegalArgumentException("배달비는 0~10000원 사이입니다.");
        }

        if (requestDto.getDeliveryFee() % 500 != 0 ) {
            throw new IllegalArgumentException("500원 단위로 입력가능합니다.");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        return repository.save(restaurant);
    }

    public List<Restaurant> getRestaurantsList() {
        return repository.findAll();
    }
}
