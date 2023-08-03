package com.example.appointment.repository;

import com.example.appointment.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByStoreName(String StoreName);

    boolean existsByStoreName(String StoreName);
}
