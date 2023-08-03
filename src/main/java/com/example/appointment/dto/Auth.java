package com.example.appointment.dto;

import com.example.appointment.domain.Partner;
import lombok.Data;

import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String partnerName;
        private String password;
    }

    @Data
    public static class SignUp {
        private String partnerName;
        private String password;
        private List<String> roles;

        public Partner toEntity(){
            return Partner.builder()
                    .partnerName(this.partnerName)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }
}
