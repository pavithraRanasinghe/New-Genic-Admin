package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AdminDeliveryService {

    ResponseEntity<?> addDelivery(DeliveryRequestDTO deliveryRequestDTO,long adminId);

    ResponseEntity<?> getDelivery();
}
