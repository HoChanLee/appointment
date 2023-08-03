package com.example.appointment.controller;

import com.example.appointment.domain.Appointment;
import com.example.appointment.dto.AppointmentDto;
import com.example.appointment.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    /**
     * 예약 등록
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDto appointmentDto){
        Appointment result = appointmentService.addAppointment(appointmentDto);
        return ResponseEntity.ok(result);
    }

    /**
     * 매장방문(키오스크를 통한 방문 확인)
     */
    @PatchMapping("/visit/{id}")
    public ResponseEntity<?> visitStore(@PathVariable Long id){
        Appointment result = appointmentService.visitStore(id);
        return ResponseEntity.ok(result);
    }
}
