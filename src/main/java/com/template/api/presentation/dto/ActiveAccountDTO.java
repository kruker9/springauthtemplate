package com.template.api.presentation.dto;

public record ActiveAccountDTO(
        String email,
        String verificationCode
) { }