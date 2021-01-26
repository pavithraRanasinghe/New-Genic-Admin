package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(Principal principal){
        LOGGER.info("request | getAllOrders | adminId: {}",principal.getName());
        ResponseEntity<?> allOrders = orderService.getAllOrders();
        LOGGER.info("response | getAllOrders | allOrders: {}",allOrders.getStatusCode());
        return allOrders;
    }
}
