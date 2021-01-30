package lk.robot.newgenicadmin.service.impl;

import lk.robot.newgenicadmin.dto.request.UserRequestDTO;
import lk.robot.newgenicadmin.dto.request.UserUpdateRequestDTO;
import lk.robot.newgenicadmin.dto.response.SignInResponseDTO;
import lk.robot.newgenicadmin.entity.AdminEntity;
import lk.robot.newgenicadmin.enums.Role;
import lk.robot.newgenicadmin.exception.CustomException;
import lk.robot.newgenicadmin.jwt.AuthenticationRequest;
import lk.robot.newgenicadmin.jwt.JwtGenerator;
import lk.robot.newgenicadmin.repository.AdminRepository;
import lk.robot.newgenicadmin.service.AdminService;
import lk.robot.newgenicadmin.util.DateConverter;
import lk.robot.newgenicadmin.util.EntityToDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final PasswordEncoder passwordEncoder;
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(PasswordEncoder passwordEncoder,
                            AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Override
    public ResponseEntity<?> adminSignUp(UserRequestDTO userRequestDTO) {
        try{
            AdminEntity adminEntity = EntityToDto.adminDtoToEntity(userRequestDTO);
            adminEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            adminEntity.setRegisteredDate(DateConverter.localDateToSql(LocalDate.now()));
            adminEntity.setRegisteredTime(DateConverter.localTimeToSql(LocalTime.now()));

            AdminEntity save = adminRepository.save(adminEntity);
            if (save.equals(null)){
                return new ResponseEntity<>("Admin saved failed",HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Admin saved to system", HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException("Admin sign up failed");
        }
    }

    @Override
    public ResponseEntity<?> adminSignIn(AuthenticationRequest authenticationRequest) {
        try{
            if (authenticationRequest.equals(null)){
                return new ResponseEntity<>("Username & Password not found",HttpStatus.BAD_REQUEST);
            }
            AdminEntity admin = adminRepository.findByUsername(authenticationRequest.getUsername());
            if (admin.equals(null)){
                return new ResponseEntity<>("User name doesn't match",HttpStatus.UNAUTHORIZED);
            }
            if (passwordEncoder.matches(authenticationRequest.getPassword(),admin.getPassword())){
                String token = generateToken(admin);
                if (token.equals(null)){
                    return new ResponseEntity<>("Token not generated",HttpStatus.UNAUTHORIZED);
                }
                SignInResponseDTO signInResponseDTO = new SignInResponseDTO(
                        token,
                        admin.getAdminId(),
                        admin.getUsername(),
                        LocalDate.now(),
                        LocalTime.now()
                );
                return new ResponseEntity<>(signInResponseDTO,HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid user credential",HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateAdmin(UserUpdateRequestDTO userUpdateRequestDTO, long adminId) {
        try{
            Optional<AdminEntity> admin = adminRepository.findById(adminId);
            if (!userUpdateRequestDTO.equals(null)){
                AdminEntity adminEntity = new AdminEntity();
                adminEntity.setAdminId(admin.get().getAdminId());
                adminEntity.setFirstName(userUpdateRequestDTO.getFirstName());
                adminEntity.setLastName(userUpdateRequestDTO.getLastName());
                adminEntity.setGender(userUpdateRequestDTO.getGender());
                adminEntity.setGmail(userUpdateRequestDTO.getGmail());
                adminEntity.setMobile(userUpdateRequestDTO.getMobile());

                AdminEntity save = adminRepository.save(adminEntity);
                if (save.equals(null)){
                    return new ResponseEntity<>("Admin not updated",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("Admin saved",HttpStatus.OK);

            }else {
                return new ResponseEntity<>("User update details not found",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            throw new CustomException(e.getMessage());
        }
    }

    private String generateToken(AdminEntity adminEntity){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+Role.ADMIN.toString()));
        return JwtGenerator.generateToken(adminEntity.getUsername(),Long.toString(adminEntity.getAdminId()),authorities);
    }
}
