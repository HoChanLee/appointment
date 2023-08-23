package com.example.appointment.repository;

import com.example.appointment.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName); //회원정보 찾기

    boolean existsByUserName(String userName); //회원 이름 중복 확인
}
