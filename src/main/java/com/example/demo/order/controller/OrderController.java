package com.example.demo.order.controller;

import com.example.demo.account.service.AccountService;
import com.example.demo.order.controller.form.OrderListRequestForm;
import com.example.demo.order.controller.form.OrderListResponseForm;
import com.example.demo.order.controller.form.OrderRequestForm;
import com.example.demo.order.controller.form.OrderResponseForm;
import com.example.demo.order.service.OrderService;
import com.example.demo.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    final private OrderService orderService;
    final private AccountService accountService;
    final private RedisService redisService;

    @PostMapping("/order-product")
    public void orderRegister(@RequestBody OrderResponseForm responseForm) {
        Long accountId = redisService.getValueByKey(responseForm.getUserToken());
        OrderRequestForm requestForm = new OrderRequestForm(responseForm.getProductId(), accountId);
        orderService.register(requestForm);
    }

    @GetMapping("/list/{userToken}")
    public List<OrderListResponseForm> orderList (@PathVariable("userToken") String userToken) {

        Long accountId = redisService.getValueByKey(userToken);
        return orderService.findAllAccountWhoBuyProduct(accountId);
    }
}
