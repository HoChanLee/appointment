package com.example.appointment.controller;

import com.example.appointment.domain.Store;
import com.example.appointment.dto.StoreDto;
import com.example.appointment.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 매장 등록
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('PARTNER')")
    public ResponseEntity<?> addStore(@RequestBody StoreDto storeDto){
        Store entity = storeDto.toEntity();
        Store result = storeService.addStore(entity);
        return ResponseEntity.ok(result);
    }

    /**
     * 매장 전체 보기
     */
    @GetMapping
    public ResponseEntity<?> searchStore (final Pageable pageable){
        Page<Store> result = this.storeService.getAllStore(pageable);
        return ResponseEntity.ok(result);
    }

    /**
     * 매장 자세히 보기
     */
    @GetMapping("/{storeName}")
    public ResponseEntity<?> searchDetailsStore(@PathVariable String storeName){
        Store result = this.storeService.getDetailsStore(storeName);
        return ResponseEntity.ok(result);
    }
}
