package com.example.appointment.service;

import com.example.appointment.domain.Partner;
import com.example.appointment.dto.Auth;
import com.example.appointment.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PartnerAuthService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final PartnerRepository partnerRepository;

    @Override
    public UserDetails loadUserByUsername(String partnerName) throws UsernameNotFoundException {
        return this.partnerRepository.findByPartnerName(partnerName)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + partnerName));
    }

    // 파트너 회원가입
    public Partner register(Auth.SignUp partner){
        boolean exists = this.partnerRepository.existsByPartnerName(partner.getPartnerName());
        if(exists){
            throw new RuntimeException("이미 사용 중인 아이디 입니다.");
        }

        partner.setPassword(this.passwordEncoder.encode(partner.getPassword())); //비밀번호 인코딩
        Partner result = this.partnerRepository.save(partner.toEntity());

        return result;
    }

    // 로그인시 검증
    public Partner authenticate(Auth.SignIn partner){
        Partner user = this.partnerRepository.findByPartnerName(partner.getPartnerName())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        if(!this.passwordEncoder.matches(partner.getPassword(), user.getPassword())){ //password를 인코딩하여 비교
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
