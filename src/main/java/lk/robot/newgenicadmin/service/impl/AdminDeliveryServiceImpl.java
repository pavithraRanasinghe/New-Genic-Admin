package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import lk.robot.newgenicadmin.dto.response.DeliveryCostDTO;
import lk.robot.newgenicadmin.dto.response.DeliveryResponseDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;
import lk.robot.newgenicadmin.entity.DeliveryCostEntity;
import lk.robot.newgenicadmin.entity.DeliveryEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.AdminRepository;
import lk.robot.newgenicadmin.repository.DeliveryCostRepository;
import lk.robot.newgenicadmin.repository.DeliveryRepository;
import lk.robot.newgenicadmin.service.AdminDeliveryService;
import lk.robot.newgenicadmin.util.DateConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminDeliveryServiceImpl implements AdminDeliveryService {

    private final ModelMapper modelMapper;
    private AdminRepository adminRepository;
    private DeliveryCostRepository deliveryCostRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public AdminDeliveryServiceImpl(AdminRepository adminRepository,
                                    ModelMapper modelMapper,
                                    DeliveryCostRepository deliveryCostRepository,
                                    DeliveryRepository deliveryRepository) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.deliveryCostRepository = deliveryCostRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public ResponseEntity<?> addDelivery(DeliveryRequestDTO deliveryRequestDTO, long adminId) {
        try{
            Optional<AdminEntity> adminEntity = adminRepository.findById(adminId);
            if (deliveryRequestDTO != null){
                if (deliveryRequestDTO.getDeliveryCostDTO().getDeliveryCostId() == 0){
                    DeliveryCostEntity deliveryCostEntity = modelMapper.map(deliveryRequestDTO.getDeliveryCostDTO(), DeliveryCostEntity.class);
                    DeliveryCostEntity deliveryCost = deliveryCostRepository.save(deliveryCostEntity);
                    if (deliveryCost != null){
                        DeliveryEntity deliveryEntity = deliveryDtoToEntity(deliveryRequestDTO, adminEntity.get(), deliveryCost);
                        deliveryRepository.save(deliveryEntity);
                        return new ResponseEntity<>("Delivery added to successful",HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("Delivery details & Delivery Cost not saved", HttpStatus.BAD_REQUEST);
                    }
                }else {
                    Optional<DeliveryCostEntity> deliveryCost = deliveryCostRepository.findById(deliveryRequestDTO.getDeliveryCostDTO().getDeliveryCostId());
                    if (deliveryCost.isPresent()){
                        DeliveryEntity deliveryEntity = deliveryDtoToEntity(deliveryRequestDTO, adminEntity.get(), deliveryCost.get());
                        deliveryRepository.save(deliveryEntity);
                        return new ResponseEntity<>("Delivery added to successful",HttpStatus.OK);
                    }else {
                        return new ResponseEntity<>("Delivery details & Delivery Cost not saved", HttpStatus.BAD_REQUEST);
                    }
                }
            }else {
                return new ResponseEntity<>("Delivery details not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getDelivery() {
        try{
            List<DeliveryEntity> all = deliveryRepository.findAll();
            List<DeliveryResponseDTO> responseDTO = new ArrayList<>();
            if (!all.isEmpty()){
                for (DeliveryEntity deliveryEntity :
                        all) {
                    DeliveryResponseDTO deliveryResponse = modelMapper.map(deliveryEntity, DeliveryResponseDTO.class);
                    deliveryResponse.setDeliveryCostDTO(modelMapper.map(deliveryEntity.getDeliveryCostEntity(), DeliveryCostDTO.class));
                    responseDTO.add(deliveryResponse);
                }
                return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }else {
               return new ResponseEntity<>("Delivery not found",HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private DeliveryEntity deliveryDtoToEntity(DeliveryRequestDTO deliveryRequestDTO,AdminEntity adminEntity,DeliveryCostEntity deliveryCost){
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setName(deliveryRequestDTO.getName());
        deliveryEntity.setAddress(deliveryRequestDTO.getAddress());
        deliveryEntity.setGmail(deliveryRequestDTO.getGmail());
        deliveryEntity.setMobile(deliveryRequestDTO.getMobile());
        deliveryEntity.setWebsite(deliveryRequestDTO.getWebSite());
        deliveryEntity.setRegistrationNumber(deliveryRequestDTO.getRegNo());
        deliveryEntity.setRegistrationDate(DateConverter.localDateToSql(LocalDate.now()));
        deliveryEntity.setRegistrationTime(DateConverter.localTimeToSql(LocalTime.now()));
        deliveryEntity.setAdminEntity(adminEntity);
        deliveryEntity.setDeliveryCostEntity(deliveryCost);

        return deliveryEntity;
    }
}
