package com.assessment.ticketing.authManagement.domain.response;

import com.assessment.ticketing.global.document.BaseDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse extends BaseDocument {
    private String token;
    private String username;

    public static AuthResponse of (String token, String username){
        return AuthResponse.builder()
                .token(token)
                .username(username)
                .build();
    }

}
