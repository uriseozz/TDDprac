package com.oderprojecj.orderproject.repository;

import com.oderprojecj.orderproject.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {

}
