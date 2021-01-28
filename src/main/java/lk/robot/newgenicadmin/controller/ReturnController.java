package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.service.ReturnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
