package com.oderprojecj.orderproject.service;

import com.oderprojecj.orderproject.dto.FoodRequestDto;
import com.oderprojecj.orderproject.model.Food;
import com.oderprojecj.orderproject.model.Restaurant;
import com.oderprojecj.orderproject.repository.FoodRepository;
import com.oderprojecj.orderproject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository repository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerFood(Long restaurantId, List<FoodRequestDto> requestDtoList) {

        //restaurantId 가져오기
         Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                 () -> new IllegalArgumentException("음식점이 존재하지 않습니다."));

//        List<Food> foods = new ArrayList<>();

        for (FoodRequestDto requestDto : requestDtoList) {
            String name = requestDto.getName();
            int price = requestDto.getPrice();

            //같은이름 찾기
            if (repository.existsByRestaurantAndName(restaurant, name)) {
                throw new IllegalArgumentException("같은 이름은 사용할 수 없습니다.");
            }
            if (price < 100 || price > 1000000) {
                throw new IllegalArgumentException("음식 가격은 100~100000까지 설정 가능합니다.");
            }
            if (price % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력가능합니다.");
            }

            Food food = new Food(restaurant, requestDto);

            repository.save(food);
        }
    }

    @Transactional
    public List<Food> findFoodList(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("음식점이 존재하지 않습니다.")
        );
        return repository.findAllByRestaurant(restaurant);
    }

}
