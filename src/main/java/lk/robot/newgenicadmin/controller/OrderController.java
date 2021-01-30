package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.ShipOrderRequestDTO;
import lk.robot.newgenicadmin.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{type}")
    public ResponseEntity<?> getOrders(@PathVariable String type, Principal principal) {
        LOGGER.info("request | getAllOrders | type: {} | adminId: {}", type, principal.getName());
        ResponseEntity<?> allOrders = orderService.getOrders(type);
        LOGGER.info("response | getAllOrders | allOrders: {}", allOrders.getStatusCode());
        return allOrders;
    }

    @PatchMapping("/shipOrder/{orderId}")
    public ResponseEntity<?> shipOrder(@RequestBody ShipOrderRequestDTO shipOrderRequestDTO, Principal principal){
        LOGGER.info("request - admin | shipOrder  | shipOrderRequest: {} | adminId: {}",shipOrderRequestDTO,principal.getName());
        ResponseEntity<?> responseEntity = orderService.shipOrder(shipOrderRequestDTO);
        LOGGER.info("response -admin | shipOrder | response: {}",responseEntity.getBody());
        return responseEntity;
    }

    @PatchMapping("/deliveredOrder")
    public ResponseEntity<?> deliveredOrder(@PathVariable long orderId,Principal principal){
        LOGGER.info("request - admin | deliveredOrder | orderId: {} | adminId: {}",orderId,principal.getName());
        ResponseEntity<?> responseEntity = orderService.deliveredOrder(orderId);
        LOGGER.info("response - admin | deliveredOrder | response: {}",responseEntity.getBody());
        return responseEntity;
    }
}
