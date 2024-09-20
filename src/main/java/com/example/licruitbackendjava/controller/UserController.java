package com.example.licruitbackendjava.controller;

import com.example.licruitbackendjava.dto.user.*;
import com.example.licruitbackendjava.exception.token.MissingRefreshHeaderException;
import com.example.licruitbackendjava.response.SuccessResponse;
import com.example.licruitbackendjava.service.TokenService;
import com.example.licruitbackendjava.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

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

    @PostMapping("/company-number")
    public ResponseEntity<SuccessResponse> checkCompanyNumberDuplication(@RequestBody @Valid CompanyNumberRequest companyNumberRequest) {
        userService.checkCompanyNumberDuplicate(companyNumberRequest.getCompanyNumber());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse());
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponse> getNewAccessToken(
            @RequestHeader(value = "refresh", required = false, defaultValue = "")
            String refreshToken
    ) {
        if(refreshToken.isEmpty()) {
            throw new MissingRefreshHeaderException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(tokenService.createNewAccessToken(refreshToken));
    }
}
