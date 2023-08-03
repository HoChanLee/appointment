package com.example.appointment.service;

import com.example.appointment.domain.Appointment;
import com.example.appointment.domain.Review;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;

    //리뷰
    public Review addReview(Review review){
        Appointment appointment = appointmentRepository.findById(review.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("예약 id확인"));

        //방문 확인
        if(appointment.isVisited()){
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("방문을 하지 않은 예약입니다.");
        }
    }
}
