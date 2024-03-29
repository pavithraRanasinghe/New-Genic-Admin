package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.CategoryRequestDTO;
import lk.robot.newgenicadmin.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, Principal principal){
        LOGGER.info("request - admin | addCategory | categoryRequest: {} | adminId: {}",categoryRequestDTO,principal.getName());
        String adminId = principal.getName();
        ResponseEntity<?> category = categoryService.addCategory(categoryRequestDTO, adminId);
        LOGGER.info("response - admin | addCategory | category: {}",category.getBody());
        return category;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(Principal principal){
        LOGGER.info("request - admin | getCategory | adminId: {}",principal.getName());
        ResponseEntity<?> all = categoryService.getCategory();
        LOGGER.info("response - admin | getCategory | categories: {}",all.getStatusCode());
        return all;
    }
}
