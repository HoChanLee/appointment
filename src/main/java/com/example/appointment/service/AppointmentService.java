package com.example.appointment.service;

import com.example.appointment.domain.Appointment;
import com.example.appointment.domain.User;
import com.example.appointment.domain.Store;
import com.example.appointment.dto.AppointmentDto;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.repository.UserRepository;
import com.example.appointment.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    //매장 예약
    public Appointment addAppointment(AppointmentDto appointmentDto){
        Optional<User> user = userRepository.findById(appointmentDto.getUserId());
        String userName = user.get().getUsername();

        Optional<Store> store = storeRepository.findById(appointmentDto.getStoreId());
        String storeName = store.get().getStoreName();

        //권한 확인
        boolean isUser = false;
        for(String item : user.get().getRoles()){
            if(item.equals("ROLE_USER")){
                isUser = true;
            }
        }
        if(!isUser){
            throw new RuntimeException("예약 권한이 없습니다.");
        }

        Appointment appointment = Appointment.builder()
                .userId(appointmentDto.getUserId())
                .storeId(appointmentDto.getStoreId())
                .userName(userName)
                .storeName(storeName)
                .regiDate(LocalDateTime.now())
                .build();

        Appointment save = appointmentRepository.save(appointment);
        return save;
    }

    //매장방문
    public Appointment visitStore(Long id){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("예약 id확인"));

        LocalDateTime checkTime = LocalDateTime.now().minusMinutes(10);
        // 예약시간이 현재시간-10분 보다 클 경우 (10분이 지나기 전)
        if(appointment.getRegiDate().isAfter(checkTime)){
            appointment.setVisited(true);
            Appointment save = appointmentRepository.save(appointment);
            return save;
        } else {
            throw new RuntimeException("예약시간 10분이 지났습니다.");
        }

    }
}
