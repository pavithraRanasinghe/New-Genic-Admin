package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.ShipOrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> getOrders(String type);

    ResponseEntity<?> shipOrder(ShipOrderRequestDTO shipOrderRequestDTO);

    ResponseEntity<?> deliveredOrder(long orderId);
}
