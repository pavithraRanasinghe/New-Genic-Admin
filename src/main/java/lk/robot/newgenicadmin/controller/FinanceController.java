package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.service.FinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/finance")
public class FinanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FinanceController.class);
    private FinanceService financeService;

    @GetMapping
    public ResponseEntity<?> findProfit(Principal principal){
        LOGGER.info("request - admin | findProfit | adminId: {}",principal.getName());
        ResponseEntity<?> profit = financeService.findProfit();
        LOGGER.info("response - admin | findProfit | profit: {}",profit.getStatusCode());
        return profit;
    }
}
