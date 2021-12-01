package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.FoodRequestDto;
import lombok.Builder;
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
public class Food {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

//    @OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
//    List<OrderFood> orderFoods = new ArrayList<>();

    public Food(Restaurant restaurant, FoodRequestDto requestDto) {
        this.restaurant = restaurant;
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }

    @Builder //안전한 객체 생성이 가능하다. 객체 초기 생성 시 사용, 이후 값변경이 필요하면 메서드를 이용한다.
    public Food(Restaurant restaurant, String name, int price){
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
    }
}
