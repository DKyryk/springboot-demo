package com.ercarts.security.web.domain;

import com.ercarts.security.web.dao.ClockEntity;
import lombok.Builder;
import lombok.Value;

/**
 * @author dkyryk
 */
@Value
@Builder
public class Clock {
    long id;
    String company;
    String colour;
    ClockType clockType;

    public static Clock fromEntity(ClockEntity entity) {
        return Clock.builder()
                .id(entity.getId())
                .clockType(entity.getClockType())
                .company(entity.getManufacturer())
                .colour(entity.getColour())
                .build();
    }
}