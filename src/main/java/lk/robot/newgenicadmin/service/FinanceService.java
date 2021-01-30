package lk.robot.newgenicadmin.service;

import org.springframework.http.ResponseEntity;

public interface FinanceService {

    ResponseEntity<?> findMonthlyFinance();
}
