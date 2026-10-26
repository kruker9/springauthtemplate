package com.template.api.presentation.dto;

public record UpdatePasswordDTO(
        String email,
        String verificationCode,
        String password
) { }