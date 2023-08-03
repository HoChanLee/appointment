package com.example.appointment.service;

import com.example.appointment.domain.Store;
import com.example.appointment.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    //매장 추가
    public Store addStore(Store store){
        boolean exists = this.storeRepository.existsByStoreName(store.getStoreName());
        if(exists){
            throw new RuntimeException("동일한 매장의 이름이 존재 합니다.");
        }
        Store save = storeRepository.save(store);
        return save;
    }

    //매장 찾기
    public Page<Store> getAllStore(Pageable pageable){
        return this.storeRepository.findAll(pageable);
    }

    //매장 자세히 보기
    public Store getDetailsStore(String storeName){
        // 1. 매장명을 기준으로 매장 정보를 조회
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new RuntimeException("매장 이름이 존제 하지 않습니다."));
        return store;
    }
}
