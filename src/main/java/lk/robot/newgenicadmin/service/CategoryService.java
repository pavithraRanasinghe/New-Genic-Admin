package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.CategoryRequestDTO;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> addCategory(CategoryRequestDTO categoryRequestDTO,String adminId);

    ResponseEntity<?> getCategory();
}
