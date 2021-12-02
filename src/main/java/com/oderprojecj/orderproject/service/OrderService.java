package com.oderprojecj.orderproject.service;


import com.oderprojecj.orderproject.dto.FoodOrderRequestDto;
import com.oderprojecj.orderproject.dto.FoodOrderResponseDto;
import com.oderprojecj.orderproject.dto.OrderRequestDto;
import com.oderprojecj.orderproject.dto.OrderResponsDto;
import com.oderprojecj.orderproject.model.Food;
import com.oderprojecj.orderproject.model.FoodOrder;
import com.oderprojecj.orderproject.model.OrderEntity;
import com.oderprojecj.orderproject.model.Restaurant;
import com.oderprojecj.orderproject.repository.FoodOrderRepository;
import com.oderprojecj.orderproject.repository.FoodRepository;
import com.oderprojecj.orderproject.repository.OrderRepository;
import com.oderprojecj.orderproject.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;

    //주문 요청
    @Transactional
    public OrderResponsDto registerOrder(OrderRequestDto orderRequestDto) {

        //음식점 ID
        Restaurant orderRestaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new IllegalArgumentException("음식점이 존재하지 않습니다.")
        );

        List<FoodOrder> order = new ArrayList<>();
        List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

        String restaurantName = orderRestaurant.getName();
        int totalPrice = 0;
        for(FoodOrderRequestDto foodOrderRequestDto : orderRequestDto.getFoods()) {
            Food food = foodRepository.findById(foodOrderRequestDto.getId()).orElseThrow(
                    () -> new IllegalArgumentException("음식이 존재하지 않습니다.")
            );
            String foodName = food.getName();
            int quantity = foodOrderRequestDto.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("주문할 음식 수량을 초과했습니다.");
            }

            int foodPrice = food.getPrice();
            int price = foodPrice * quantity;
            totalPrice += price;
            //사용자한테 보내주는 얘
            FoodOrderResponseDto foodInfo = new FoodOrderResponseDto(foodName, quantity, price);
            foodOrderResponseDtoList.add(foodInfo);

            // DB한테 저장하는 얘
            FoodOrder foodOrder = new FoodOrder(food, quantity, price);
            order.add(foodOrderRepository.save(foodOrder));
        }
        int minOrderPrice = orderRestaurant.getMinOrderPrice();
        if (totalPrice < minOrderPrice) {
            throw new IllegalArgumentException("최소주문금액보다 적습니다.");
        }

        //deliveryFee
        int deliveryFee = orderRestaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        //사용자한테 보내주는 얘
        OrderResponsDto orderResponsDto = new OrderResponsDto(restaurantName, foodOrderResponseDtoList, deliveryFee, totalPrice);

        //DB에 저장하는 얘
        OrderEntity orderEntity = new OrderEntity(restaurantName, order, totalPrice, deliveryFee, orderRestaurant);
        repository.save(orderEntity);

        return orderResponsDto;
    }

    @Transactional
    public List<OrderResponsDto> findOrderList() {

        List<OrderResponsDto> orderResponsDtoList = new ArrayList<>();
        List<OrderEntity> orderEntityList = repository.findAll();

        //Restaurant orderRestaurant = restaurantRepository.findById()
        for(OrderEntity orderEntity : orderEntityList) {
            List<FoodOrderResponseDto> foodOrderResponseDtoList = new ArrayList<>();

            String restaurantName = orderEntity.getRestaurantName();
            for (FoodOrder foodOrder : orderEntity.getFoodOrders()) {
                String foodName = foodOrder.getFood().getName();
                int quantity = foodOrder.getQuantity();
                int price = foodOrder.getPrice();

                FoodOrderResponseDto foodOrderResponseDto = new FoodOrderResponseDto(foodName, quantity, price);
                foodOrderResponseDtoList.add(foodOrderResponseDto);
            }
            int deliveryFee = orderEntity.getRestaurant().getDeliveryFee();
            int totalPrice = orderEntity.getTotalPrice();

            OrderResponsDto orderResponsDto = new OrderResponsDto(restaurantName, foodOrderResponseDtoList, deliveryFee, totalPrice);
            orderResponsDtoList.add(orderResponsDto);
        }
        return orderResponsDtoList;
    }
}
