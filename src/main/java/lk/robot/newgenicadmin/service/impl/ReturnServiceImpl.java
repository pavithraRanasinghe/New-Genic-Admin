package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.ProductDTO;
import lk.robot.newgenicadmin.dto.ReturnDetailDTO;
import lk.robot.newgenicadmin.dto.response.ReturnResponseDTO;
import lk.robot.newgenicadmin.entity.OrderDetailEntity;
import lk.robot.newgenicadmin.entity.OrderEntity;
import lk.robot.newgenicadmin.entity.ReturnDetailEntity;
import lk.robot.newgenicadmin.entity.ReturnEntity;
import lk.robot.newgenicadmin.enums.ReturnAction;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.OrderDetailRepository;
import lk.robot.newgenicadmin.repository.ReturnDetailRepository;
import lk.robot.newgenicadmin.repository.ReturnRepository;
import lk.robot.newgenicadmin.service.ReturnService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReturnServiceImpl implements ReturnService {

    private final ModelMapper modelMapper;
    private ReturnRepository returnRepository;
    private OrderDetailRepository orderDetailRepository;
    private ReturnDetailRepository returnDetailRepository;

    @Autowired
    public ReturnServiceImpl(ModelMapper modelMapper,
                             ReturnRepository returnRepository,
                             OrderDetailRepository orderDetailRepository,
                             ReturnDetailRepository returnDetailRepository) {
        this.modelMapper = modelMapper;
        this.returnRepository = returnRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.returnDetailRepository = returnDetailRepository;
    }

    @Override
    public ResponseEntity<?> getReturnRequest() {
        try {
            List<ReturnEntity> returnEntityList = returnRepository.findByAction(ReturnAction.PENDING.toString());

            if (!returnEntityList.isEmpty()) {
                List<ReturnResponseDTO> returnResponseList = new ArrayList<>();
                for (ReturnEntity returnEntity :
                        returnEntityList) {
                    List<ReturnDetailEntity> returnDetails = returnDetailRepository.findByReturnEntity(returnEntity);
                    List<ReturnDetailDTO> returnDetailList = new ArrayList<>();
                    for (ReturnDetailEntity returnDetailEntity :
                            returnDetails) {
                        returnDetailList.add(setProductDetail(returnDetailEntity));
                    }
                    returnResponseList.add(setReturnDetails(returnEntity,returnDetailList));
                }
                return new ResponseEntity<>(returnResponseList,HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No return request", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private ReturnResponseDTO setReturnDetails(ReturnEntity returnEntity,List<ReturnDetailDTO> returnDetailList) {
        return new ReturnResponseDTO(
                returnEntity.getOrderEntity().getOrderId(),
                returnEntity.getOrderEntity().getOrderDate(),
                returnEntity.getOrderEntity().getOrderTime(),
                returnEntity.getRequestDate(),
                returnEntity.getRequestTime(),
                returnDetailList
        );
    }

    private ReturnDetailDTO setProductDetail(ReturnDetailEntity returnDetail) {
        return new ReturnDetailDTO(
                returnDetail.getReason(),
                returnDetail.getReturnQty(),
                modelMapper.map(returnDetail.getOrderDetailEntity().getProductEntity(),ProductDTO.class)
        );
    }
}
