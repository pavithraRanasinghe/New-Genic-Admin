package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.ProductPriceRequestDTO;
import lk.robot.newgenicadmin.dto.request.VariationRequestDTO;
import lk.robot.newgenicadmin.dto.request.BasicProductRequestDTO;
import lk.robot.newgenicadmin.dto.request.ProductImageRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    ResponseEntity<?> addProductBasic(BasicProductRequestDTO requestDTO);

    ResponseEntity<?> addVariations(List<VariationRequestDTO> variationRequestDTOList,String productId);

    ResponseEntity<?> addProductImage(long variationDetailId, List<MultipartFile> multipartFiles);

    ResponseEntity<?> addProductDetail(List<ProductPriceRequestDTO> productPriceRequestDTOList);

    ResponseEntity<?> removeBasicProductDetail(String uuid);
}
