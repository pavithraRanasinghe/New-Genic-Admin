package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.ReorderRequest;
import org.springframework.http.ResponseEntity;

public interface ReturnService {

    ResponseEntity<?> getReturnRequest();

    ResponseEntity<?> refundReturn(long orderId);

    ResponseEntity<?> reorderReturn(ReorderRequest reorderRequest);
}
