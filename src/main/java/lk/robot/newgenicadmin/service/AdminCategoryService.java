package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.CategoryRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AdminCategoryService {

    ResponseEntity<?> addCategory(CategoryRequestDTO categoryRequestDTO,long adminId);

    ResponseEntity<?> getCategory();
}
