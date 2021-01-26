package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import lk.robot.newgenicadmin.service.AdminDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/delivery")
public class AdminDeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDeliveryController.class);
    private AdminDeliveryService adminDeliveryService;

    @Autowired
    public AdminDeliveryController(AdminDeliveryService adminDeliveryService) {
        this.adminDeliveryService = adminDeliveryService;
    }

    @PostMapping
    public ResponseEntity<?> addDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO, Principal principal){
        LOGGER.info("request - admin | addDelivery | deliveryRequest: {} | adminId: {}",deliveryRequestDTO,principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> delivery = adminDeliveryService.addDelivery(deliveryRequestDTO, adminId);
        LOGGER.info("response - admin | addDelivery | delivery: {}",delivery.getBody().toString());
        return delivery;
    }

    @GetMapping
    public ResponseEntity<?> getDelivery(Principal principal){
        LOGGER.info("request - admin | getDelivery | adminId: {}",principal.getName());
        long adminId = Long.parseLong(principal.getName());
        ResponseEntity<?> delivery = adminDeliveryService.getDelivery();
        LOGGER.info("response - admin | getDelivery | delivery: {}",delivery.getStatusCode());
        return delivery;
    }
}
