package com.example.demo.order.controller.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderResponseForm {
    final private Long productId;
    final private Long AccountId;

    public OrderRequestForm toOrderRequestForm () {
        return new OrderRequestForm(productId, AccountId);
    }
}
