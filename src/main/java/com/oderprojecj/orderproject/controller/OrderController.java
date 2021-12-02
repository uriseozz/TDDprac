package com.oderprojecj.orderproject.controller;

import com.oderprojecj.orderproject.dto.OrderRequestDto;
import com.oderprojecj.orderproject.dto.OrderResponsDto;
import com.oderprojecj.orderproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrderResponsDto registerOrder(
            @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.registerOrder(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponsDto> getOrderList() {
        return orderService.findOrderList();
    }

}
