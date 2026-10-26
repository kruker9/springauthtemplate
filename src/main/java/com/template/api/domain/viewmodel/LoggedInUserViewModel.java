package com.template.api.domain.viewmodel;

public record LoggedInUserViewModel(
        String id,
        String emailAddress,
        String accessToken,
        String refreshToken
) { }
