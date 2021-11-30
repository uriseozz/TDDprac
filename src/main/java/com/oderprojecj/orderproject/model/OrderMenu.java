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
public class OrderMenu {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

//    @Column(nullable = false)
    private Integer price;

//    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Menu menu;

    public OrderMenu(OrderRequestDto requestDto) {
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
    }

}
