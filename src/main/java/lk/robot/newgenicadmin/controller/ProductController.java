package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.ProductPriceRequestDTO;
import lk.robot.newgenicadmin.dto.request.VariationRequestDTO;
import lk.robot.newgenicadmin.dto.request.BasicProductRequestDTO;
import lk.robot.newgenicadmin.dto.request.ProductImageRequestDTO;
import lk.robot.newgenicadmin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addBasic")
    public ResponseEntity<?> addProductBasic(@RequestBody BasicProductRequestDTO requestDTO,Principal principal){
        LOGGER.info("request - admin | addProductBasic | basicProduct:{} | adminId: {}",requestDTO,principal.getName());
        ResponseEntity<?> productBasic = productService.addProductBasic(requestDTO);
        LOGGER.info("response - admin | addProductBasic | productId: {}",productBasic.getBody());
        return productBasic;
    }

    @PostMapping("/addVariations")
    public ResponseEntity<?> addVariations(@RequestBody List<VariationRequestDTO> variationRequestDTOList, Principal principal){
        LOGGER.info("request - admin | addVariations | variationList: {} | adminId: {}",variationRequestDTOList, principal.getName());
        ResponseEntity<?> combinations = productService.addVariations(variationRequestDTOList);
        LOGGER.info("response - admin | addVariations | combinations: {}",combinations.getBody());
        return combinations;
    }

    @PostMapping("/addImage")
    public ResponseEntity<?> addProductImages(@RequestParam("productImageRequestDTO") ProductImageRequestDTO productImageRequestDTO,
                                        @RequestParam("image")List<MultipartFile> multipartFiles,
                                        Principal principal){
        LOGGER.info("request - admin | addProduct | productDetail:{} | adminId: {}", productImageRequestDTO,principal.getName());
        ResponseEntity<?> addProduct = productService.addProductImage(productImageRequestDTO,multipartFiles);
        LOGGER.info("response - admin | addProduct | product: {}",addProduct.getBody());
        return addProduct;
    }

    @PostMapping("/addDetail")
    public ResponseEntity<?> addProductDetail(@RequestBody List<ProductPriceRequestDTO> productPriceRequestDTOList,Principal principal){
        LOGGER.info("request - admin | addProductDetail | productPriceRequestDTOList: {} | adminId: {}",productPriceRequestDTOList,principal.getName());
        ResponseEntity<?> productDetail = productService.addProductDetail(productPriceRequestDTOList);
        LOGGER.info("response - admin | addProductDetail | productDetail: {}",productDetail.getBody());
        return productDetail;
    }

    @DeleteMapping("/variationExit")
    public ResponseEntity<?> removeBasicProductDetail(@PathVariable String productId,Principal principal){
        LOGGER.info("request - admin | removeBasicProductDetail | productId: {} | adminId: {}",productId,principal.getName());
        ResponseEntity<?> removeBasicProductDetail = productService.removeBasicProductDetail(productId);
        LOGGER.info("response - admin | addProductDetail | removeBasicProductDetail: {}",removeBasicProductDetail);
        return removeBasicProductDetail;
    }
}
