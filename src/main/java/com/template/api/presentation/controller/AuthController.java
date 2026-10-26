package com.template.api.presentation.controller;

import an.awesome.pipelinr.Pipeline;
import com.template.api.application.usecases.auth.*;
import com.template.api.presentation.dto.*;
import com.template.api.domain.viewmodel.AccessTokenViewModel;
import com.template.api.domain.viewmodel.LoggedInUserViewModel;
import com.template.api.domain.viewmodel.IdResponse;
import com.template.api.domain.viewmodel.VoidResponse;
import com.template.api.presentation.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Pipeline pipeline;

    public AuthController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/register")
    public ResponseEntity<IdResponse> register(
            @Valid @RequestBody RegisterDTO dto
    ) {
        return new ResponseEntity<>(pipeline.send(new RegisterCommand(
                dto.email(),
                dto.password()
        )), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedInUserViewModel> login(
            @Valid @RequestBody LoginDTO dto
    ) {
        return ResponseEntity.ok(pipeline.send(new LoginCommand(
                dto.email(),
                dto.password()
        )));
    }

    @PostMapping("/activate")
    public ResponseEntity<IdResponse> activateAccount(
            @Valid @RequestBody ActiveAccountDTO dto
    ) {
        return ResponseEntity.ok(pipeline.send(new ActivateAccountCommand(
                dto.email(),
                dto.verificationCode()
        )));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenViewModel> refreshToken(
            @Valid @RequestBody RefreshTokenDTO dto
    ) {
        return ResponseEntity.ok(pipeline.send(new RefreshTokenCommand(
                dto.refreshToken()
        )));
    }

    @PostMapping("/request/password")
    public ResponseEntity<VoidResponse>  resetPasswordRequest(
            @Valid @RequestBody ResetPasswordRequestDTO dto
    ) {
        return ResponseEntity.ok(pipeline.send(new ResetPasswordRequestCommand(
                dto.email()
        )));
    }

    @PutMapping("/update/password")
    public ResponseEntity<VoidResponse> updatePassword(
            @Valid @RequestBody UpdatePasswordDTO dto
    ) {
        return ResponseEntity.ok(pipeline.send(new UpdatePasswordCommand(
                dto.email(),
                dto.verificationCode(),
                dto.password()
        )));
    }
}
