package com.example.appointment.repository;

import com.example.appointment.domain.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaPartnerRepositoryTest {
    @Autowired
    PartnerRepository jpaPartnerRepository;

    @Test
    void insertPartnerTest () {
        //given
        Partner partner = new Partner(10, "partner", "aaa", Collections.singletonList("PARTNER"));

        //when
        jpaPartnerRepository.save(partner);

        //then
        List<Partner> partnerList = jpaPartnerRepository.findAll();
        assertTrue(partnerList.size() > 0);
    }

    @Test
    void findByPartnerTest () {
        //given
        Partner partner = new Partner(10, "partner2", "bbb", Collections.singletonList("PARTNER"));

        //when
        Partner save = jpaPartnerRepository.save(partner);

        //then
        Optional<Partner> result = jpaPartnerRepository.findById(save.getId());
        assertEquals(result.get().getPartnerName(), "partner2");

    }
}