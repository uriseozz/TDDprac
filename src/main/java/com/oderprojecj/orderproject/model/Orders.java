package com.oderprojecj.orderproject.model;

import com.oderprojecj.orderproject.dto.OdersRequestDto;
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
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    //mappedBy:양방향관계 중 주인임을 명시
    //CascadeType.REMOVE:shop을 지울 때 menu리스트가 전부 삭제됨
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    List<OrderMenu> orderMenu = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;


    public Orders(OdersRequestDto requestDto) {

    }
}
