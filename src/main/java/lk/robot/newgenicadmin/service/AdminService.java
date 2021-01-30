package lk.robot.newgenicadmin.service;

import lk.robot.newgenicadmin.dto.request.UserRequestDTO;
import lk.robot.newgenicadmin.dto.request.UserUpdateRequestDTO;
import lk.robot.newgenicadmin.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<?> adminSignUp(UserRequestDTO userRequestDTO);

    ResponseEntity<?> adminSignIn(AuthenticationRequest authenticationRequest);

    ResponseEntity<?> updateAdmin(UserUpdateRequestDTO userUpdateRequestDTO,long adminId);
}
