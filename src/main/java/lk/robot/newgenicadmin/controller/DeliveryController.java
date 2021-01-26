package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import lk.robot.newgenicadmin.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);
    private DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<?> addDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO, Principal principal){
        LOGGER.info("request - admin | addDelivery | deliveryRequest: {} | adminId: {}",deliveryRequestDTO,principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> delivery = deliveryService.addDelivery(deliveryRequestDTO, adminId);
        LOGGER.info("response - admin | addDelivery | delivery: {}",delivery.getBody().toString());
        return delivery;
    }

    @GetMapping
    public ResponseEntity<?> getDelivery(Principal principal){
        LOGGER.info("request - admin | getDelivery | adminId: {}",principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> delivery = deliveryService.getDelivery();
        LOGGER.info("response - admin | getDelivery | delivery: {}",delivery.getStatusCode());
        return delivery;
    }
}
