package lk.robot.newgenicadmin.util;

import lk.robot.newgenicadmin.dto.request.UserRequestDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;

public class EntityToDto {

    public static AdminEntity adminDtoToEntity(UserRequestDTO userRequestDTO) {
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

}
