package com.example.licruitbackendjava.controller;

import com.example.licruitbackendjava.dto.user.LoginRequest;
import com.example.licruitbackendjava.dto.user.LoginResponse;
import com.example.licruitbackendjava.dto.user.RegisterRequest;
import com.example.licruitbackendjava.response.SuccessResponse;
import com.example.licruitbackendjava.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> addUser(@RequestBody @Valid RegisterRequest registerRequest) {
        userService.createUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
