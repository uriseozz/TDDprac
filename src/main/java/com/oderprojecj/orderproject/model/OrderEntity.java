package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.FoodOrderResponseDto;
import com.oderprojecj.orderproject.dto.FoodRequestDto;
import com.oderprojecj.orderproject.dto.OrderResponsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor  //기본생성자 만들 수 있음
@Getter
@Setter
@Entity  //테이블과 연계됨을 스프링에게 알려줌
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

//    @Column(nullable = false)
//    private List<FoodOrderResponseDto> foods;

    //mappedBy:양방향관계 중 주인임을 명시
    //CascadeType.REMOVE:shop을 지울 때 menu리스트가 전부 삭제됨
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FoodOrder> foodOrders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    public OrderEntity(String restaurantName, List<FoodOrder> foodOrders, int totalPrice, int deliveryFee, Restaurant restaurant) {
        this.restaurantName = restaurantName;
        this.foodOrders = foodOrders;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.restaurant = restaurant;

    }
}
