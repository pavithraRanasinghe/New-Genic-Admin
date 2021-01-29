package lk.robot.newgenicadmin.util;

import lk.robot.newgenicadmin.dto.ProductDTO;
import lk.robot.newgenicadmin.dto.request.UserRequestDTO;
import lk.robot.newgenicadmin.dto.request.UserSignUpDTO;
import lk.robot.newgenicadmin.dto.DeliveryCostDTO;
import lk.robot.newgenicadmin.dto.response.UserFeedbackDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;
import lk.robot.newgenicadmin.entity.DeliveryCostEntity;
import lk.robot.newgenicadmin.entity.ProductEntity;
import lk.robot.newgenicadmin.entity.UserEntity;

public class EntityToDto {

    public static ProductDTO productEntityToDto(ProductEntity productEntity){
        return new ProductDTO(
                productEntity.getProductId(),
                productEntity.getProductCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getStock(),
                productEntity.getColor(),
                productEntity.getSize(),
                productEntity.getGender(),
                productEntity.getWeight(),
                productEntity.getBuyingPrice(),
                productEntity.getSalePrice(),
                productEntity.getRetailPrice(),
                productEntity.getAddedDate(),
                productEntity.isActive(),
                productEntity.isFreeShipping(),
                productEntity.getDealEntity().getDiscount()
        );
    }

    public static UserFeedbackDTO userEntityToUserFeedbackDto(UserEntity userEntity){
        return new UserFeedbackDTO(
                userEntity.getUserId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getProfilePicture()
        );
    }

    public static UserEntity userDtoToEntity(UserSignUpDTO userSignUpDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userSignUpDTO.getFirstName());
        userEntity.setLastName(userSignUpDTO.getLastName());
        userEntity.setUsername(userSignUpDTO.getUserName());
        userEntity.setGmail(userSignUpDTO.getGmail());
        userEntity.setMobile(userSignUpDTO.getMobile());
        userEntity.setDob(DateConverter.stringToDate(userSignUpDTO.getDob()));
        userEntity.setPassword(userSignUpDTO.getPassword());
        userEntity.setProfilePicture(userSignUpDTO.getProfilePicture());
        userEntity.setRole(userSignUpDTO.getRole());

        return userEntity;

    }

    public static AdminEntity adminDtoToEntity(UserRequestDTO userRequestDTO){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setFirstName(userRequestDTO.getFirstName());
        adminEntity.setLastName(userRequestDTO.getLastName());
        adminEntity.setUsername(userRequestDTO.getUserName());
        adminEntity.setNic(userRequestDTO.getNic());
        adminEntity.setGender(userRequestDTO.getGender());
        adminEntity.setGmail(userRequestDTO.getGmail());
        adminEntity.setMobile(userRequestDTO.getMobile());
        adminEntity.setProfilePicture(userRequestDTO.getProfilePicture());

        return adminEntity;
    }

    public static DeliveryCostDTO deliveryCostEntityToDTO(DeliveryCostEntity deliveryCostEntity){
//        return new DeliveryCostDTO(
//                deliveryCostEntity.getDeliveryCostId(),
//                deliveryCostEntity.getGalle(),
//                deliveryCostEntity.getMatara(),
//                deliveryCostEntity.getHambantota(),
//                deliveryCostEntity.getKaluthra(),
//                deliveryCostEntity.getColombo(),
//                deliveryCostEntity.getGampaha(),
//                deliveryCostEntity.getPuttalm(),
//                deliveryCostEntity.getKurunegala(),
//                deliveryCostEntity.getJaffna(),
//                deliveryCostEntity.getKilinochchi(),
//                deliveryCostEntity.getMannar(),
//                deliveryCostEntity.getMullaitivu(),
//                deliveryCostEntity.getVavuniya(),
//                deliveryCostEntity.getAnuradhapura(),
//                deliveryCostEntity.getPolonnaruwa(),
//                deliveryCostEntity.getMatale(),
//                deliveryCostEntity.getKandy(),
//                deliveryCostEntity.getNuwaraEliya(),
//                deliveryCostEntity.getRatnapura(),
//                deliveryCostEntity.getKegalle(),
//                deliveryCostEntity.getTrincomalee(),
//                deliveryCostEntity.getBatticaloa(),
//                deliveryCostEntity.getAmpara(),
//                deliveryCostEntity.getMonaragala(),
//                deliveryCostEntity.getBadulla(),
//                deliveryCostEntity.getCostPerExtra()
//        );
        return null;
    }
}
