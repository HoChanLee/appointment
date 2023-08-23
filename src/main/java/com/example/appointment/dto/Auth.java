package com.example.appointment.dto;

import com.example.appointment.domain.User;
import lombok.Data;

import java.util.List;

public class Auth {

    @Data
    public static class SignIn {
        private String userName;
        private String password;
    }

    @Data
    public static class SignUp {
        private String userName;
        private String password;
        private List<String> roles;

        public User toEntity(){
            return User.builder()
                    .userName(this.userName)
                    .password(this.password)
                    .roles(this.roles)
                    .build();
        }
    }
}
