package lk.robot.newgenicadmin.controller;

import lk.robot.newgenicadmin.dto.request.UserRequestDTO;
import lk.robot.newgenicadmin.jwt.AuthenticationRequest;
import lk.robot.newgenicadmin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> adminSignUp(@RequestBody UserRequestDTO userRequestDTO, Principal principal){
        LOGGER.info("request - admin | adminSignUp | userRequest: {} ",userRequestDTO);
        ResponseEntity<?> adminSignUp = adminService.adminSignUp(userRequestDTO);
        LOGGER.info("response - admin | adminSignUp | signUp: {}",adminSignUp.getBody().toString());
        return adminSignUp;
    }

    @PostMapping("logIn")
    public ResponseEntity<?> adminSignIn(@RequestBody AuthenticationRequest authenticationRequest){
        LOGGER.info("request - admin | adminSignIn | authenticationRequest: {} ",authenticationRequest);
        ResponseEntity<?> adminSignIn = adminService.adminSignIn(authenticationRequest);
        LOGGER.info("response - admin | adminSignIn | signIn: {}",adminSignIn);
        return adminSignIn;
    }
}
