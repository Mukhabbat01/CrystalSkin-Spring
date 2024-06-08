package com.crystalskin.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.crystalskin.config.JwtProperties;
import com.crystalskin.domain.User;
import com.crystalskin.dto.request.CreateAccessTokenRequest;
import com.crystalskin.dto.response.CreateAccessTokenResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JWTokenProviderService tokenProvider;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    public CreateAccessTokenResponse createAccessToken(CreateAccessTokenRequest request) {
        // Create Access Token  by usrId and password
        if (request.getUsrId() != null && request.getPass() != null) {
            User user = userService.findById(request.getUsrId());
            if (user != null && user.getPass().equals(request.getPass())) {
                String accessToken = tokenProvider.createToken(user, Duration.ofMinutes(jwtProperties.getDuration()));
                System.out.println(accessToken);
                return new CreateAccessTokenResponse(accessToken);
            }
        }
        // If user ID or password is null or invalid, return null or handle error
        return null;
    }
}
