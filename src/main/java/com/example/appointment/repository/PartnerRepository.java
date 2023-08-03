package com.example.appointment.repository;

import com.example.appointment.domain.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByPartnerName(String partnerName); //회원정보 찾기

    boolean existsByPartnerName(String partnerName); //회원 이름 중복 확인
}
