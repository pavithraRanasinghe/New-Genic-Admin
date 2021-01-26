package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.ProductRequestDTO;
import lk.robot.newgenicadmin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody List<ProductRequestDTO> productRequestList, Principal principal){
        // TODO: 20/01/2021 Didn't add image!!!!!! Should add it
        LOGGER.info("request - admin | addProduct | productList:{} | adminId: {}",productRequestList,principal.getName());
        ResponseEntity<?> addProduct = productService.addProduct(productRequestList);
        LOGGER.info("response - admin | addProduct | product: {}",addProduct.getBody().toString());
        return addProduct;
    }
}
