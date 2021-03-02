package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.DeliveryCostDTO;
import lk.robot.newgenicadmin.dto.request.DeliveryCostRequestDTO;
import lk.robot.newgenicadmin.dto.request.DeliveryRequestDTO;
import lk.robot.newgenicadmin.dto.response.DeliveryResponseDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;
import lk.robot.newgenicadmin.entity.DeliveryCostEntity;
import lk.robot.newgenicadmin.entity.DeliveryEntity;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.repository.AdminRepository;
import lk.robot.newgenicadmin.repository.DeliveryCostRepository;
import lk.robot.newgenicadmin.repository.DeliveryRepository;
import lk.robot.newgenicadmin.service.DeliveryService;
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
public class DeliveryServiceImpl implements DeliveryService {

    private final ModelMapper modelMapper;
    private AdminRepository adminRepository;
    private DeliveryCostRepository deliveryCostRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryServiceImpl(AdminRepository adminRepository,
                               ModelMapper modelMapper,
                               DeliveryCostRepository deliveryCostRepository,
                               DeliveryRepository deliveryRepository) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.deliveryCostRepository = deliveryCostRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public ResponseEntity<?> addDelivery(DeliveryRequestDTO deliveryRequestDTO, String adminId) {
        try{
            Optional<AdminEntity> adminEntity = adminRepository.findByUuid(adminId);
            if (deliveryRequestDTO != null){
                DeliveryEntity deliveryEntity = deliveryDtoToEntity(deliveryRequestDTO, adminEntity.get());
                DeliveryEntity delivery = deliveryRepository.save(deliveryEntity);
                if (!delivery.equals(null)){
                    for (DeliveryCostRequestDTO deliveryCostDTO :
                            deliveryRequestDTO.getDeliveryCostList()) {
                        DeliveryCostEntity costEntity = modelMapper.map(deliveryCostDTO, DeliveryCostEntity.class);
                        costEntity.setDeliveryEntity(delivery);

                        deliveryCostRepository.save(costEntity);
                    }
                    return new ResponseEntity<>("Deliver added successful",HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Delivery added not complete",HttpStatus.BAD_REQUEST);
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
                    List<DeliveryCostEntity> byDeliveryEntity = deliveryCostRepository.findByDeliveryEntity(deliveryEntity);
                    List<DeliveryCostDTO> costList = new ArrayList<>();
                    for (DeliveryCostEntity deliveryCostEntity :
                            byDeliveryEntity) {
                        costList.add(modelMapper.map(deliveryCostEntity, DeliveryCostDTO.class));
                    }
                    deliveryResponse.setDeliveryCostDTOList(costList);
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

    private DeliveryEntity deliveryDtoToEntity(DeliveryRequestDTO deliveryRequestDTO, AdminEntity adminEntity){
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setName(deliveryRequestDTO.getName());
        deliveryEntity.setAddress(deliveryRequestDTO.getAddress());
        deliveryEntity.setGmail(deliveryRequestDTO.getGmail());
        deliveryEntity.setMobile(deliveryRequestDTO.getMobile());
        deliveryEntity.setWebsite(deliveryRequestDTO.getWebSite());
        deliveryEntity.setRegistrationNumber(deliveryRequestDTO.getRegNo());
        deliveryEntity.setRegistrationDate(DateConverter.localDateToSql(LocalDate.now()));
        deliveryEntity.setRegistrationTime(DateConverter.localTimeToSql(LocalTime.now()));
        deliveryEntity.setActive(true);
        deliveryEntity.setAdminEntity(adminEntity);
        return deliveryEntity;
    }
}
