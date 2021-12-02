package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.FoodOrderRequestDto;
import com.oderprojecj.orderproject.dto.FoodOrderResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor  //기본생성자 만들 수 있음
@Getter
@Setter
@Entity  //테이블과 연계됨을 스프링에게 알려줌
public class FoodOrder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Food food;

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private OrderEntity orderEntity;


    public FoodOrder(Food food, int quantity, int price) {
        this.food = food;
        this.quantity = quantity;
        this.price = price;

    }

}
