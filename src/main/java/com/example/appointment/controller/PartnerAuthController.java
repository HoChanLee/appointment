package com.example.appointment.controller;

import com.example.appointment.domain.Partner;
import com.example.appointment.dto.Auth;
import com.example.appointment.security.TokenProvider;
import com.example.appointment.service.PartnerAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PartnerAuthController {
    private final PartnerAuthService partnerAuthService;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입 API
     * @param request
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) {
        Partner register = this.partnerAuthService.register(request);
        return ResponseEntity.ok(register);
    }

    /**
     * 로그인 API
     * 토큰 반환 (파트너이름, 권한)
     * @param request
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
        Partner partner = this.partnerAuthService.authenticate(request);
        String token = this.tokenProvider.generateToken(partner.getPartnerName(), partner.getRoles());
        return ResponseEntity.ok(token);
    }

}
