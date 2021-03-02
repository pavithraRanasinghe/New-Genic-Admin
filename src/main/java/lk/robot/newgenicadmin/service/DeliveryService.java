package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import org.springframework.http.ResponseEntity;

public interface DeliveryService {

    ResponseEntity<?> addDelivery(DeliveryRequestDTO deliveryRequestDTO,String adminId);

    ResponseEntity<?> getDelivery();
}
