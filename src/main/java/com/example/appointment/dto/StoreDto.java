package com.example.appointment.dto;

import com.example.appointment.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private String storeName;
    private String storePosition;
    private String storeDescription;

    public Store toEntity(){
        return Store.builder()
                .storeName(this.storeName)
                .storePosition(this.storePosition)
                .storeDescription(this.storeDescription)
                .build();
    }
}
