package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.ReorderRequest;
import lk.robot.newgenicadmin.service.ReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/return")
public class ReturnController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReturnController.class);
    private ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReturnRequests(Principal principal){
        LOGGER.info("request - admin | getAllReturnRequest | adminId: {}",principal.getName());
        ResponseEntity<?> returnRequest = returnService.getReturnRequest();
        LOGGER.info("response - admin | getAllReturnRequest | returnRequest: {}",returnRequest.getStatusCode());
        return returnRequest;
    }

    @PostMapping("/refund/{orderId}")
    public ResponseEntity<?> refundReturn(@PathVariable long orderId, Principal principal){
        LOGGER.info("request - admin | refundReturn | orderId: {} | adminId: {}",orderId,principal.getName());
        ResponseEntity<?> refund = returnService.refundReturn(orderId);
        LOGGER.info("response - admin | refundReturn | refundRequest: {}",refund.getBody().toString());
        return refund;
    }

    @PostMapping("/reorder")
    public ResponseEntity<?> reorderReturn(@RequestBody ReorderRequest reorderRequest, Principal principal){
        LOGGER.info("request - admin | reorderReturn | reorderRequest: {} | adminId: {}",reorderRequest,principal.getName());
        ResponseEntity<?> reorder = returnService.reorderReturn(reorderRequest);
        LOGGER.info("response - admin | reorderReturn | reorder: {}",reorder.getBody());
        return reorder;
    }
}
