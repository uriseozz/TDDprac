package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor  //기본생성자 만들 수 있음
@Getter
@Setter
@Entity  //테이블과 연계됨을 스프링에게 알려줌
public class OrderFood {
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

    public OrderFood(OrderRequestDto requestDto, Food food) {
        this.food = food;
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
    }

}
