package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.ProductRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminProductService {

    ResponseEntity<?> addProduct(List<ProductRequestDTO> productRequestList);
}
