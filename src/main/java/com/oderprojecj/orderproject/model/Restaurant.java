package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.RestaurantRequestDto;
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
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    //mappedBy:양방향관계 중 주인임을 명시
    //CascadeType.REMOVE:shop을 지울 때 menu리스트가 전부 삭제됨
    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<Menu> menu = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<Orders> orders = new ArrayList<>();


    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

    @Builder //안전한 객체 생성이 가능하다. 객체 초기 생성 시 사용, 이후 값변경이 필요하면 메서드를 이용한다.
    public Restaurant(String name, Integer minOrderPrice, Integer deliveryFee){
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
