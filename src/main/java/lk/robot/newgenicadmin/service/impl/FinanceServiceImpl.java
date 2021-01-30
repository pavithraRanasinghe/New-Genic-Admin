package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.PaymentRepository;
import lk.robot.newgenicadmin.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FinanceServiceImpl implements FinanceService {

    private PaymentRepository paymentRepository;

    @Autowired
    public FinanceServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ResponseEntity<?> findMonthlyFinance() {
        try{
            return null;
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }
}
