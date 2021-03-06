package com.ercarts.clock.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ercarts.clock.dao.ClockEntity;
import com.ercarts.clock.domain.Clock;
import com.ercarts.clock.domain.ClockType;
import com.ercarts.clock.error.ClockAppError;
import com.ercarts.clock.model.ClockRegistration;
import com.ercarts.clock.repository.ClockRepository;
import org.springframework.stereotype.Service;

/**
 * @author dkyryk
 */
@Service
public class ClockService {

    private final ClockRepository clockRepository;

    public ClockService(ClockRepository clockRepository) {
        this.clockRepository = clockRepository;
    }

    public List<Clock> getAllClocks() {
        return clockRepository.findAll()
                .stream()
                .map(ClockService::buildClockFromEntity)
                .collect(Collectors.toList());
    }

    public Clock getClock(long id) {
        return clockRepository.findById(id)
                .map(ClockService::buildClockFromEntity)
                .orElseThrow(() -> ClockAppError.CLOCK_NOT_FOUND.toException(id));
    }

    public Clock registerClock(ClockRegistration clockRegistration) {
        ClockEntity entity = new ClockEntity();
        entity.setClockType(ClockType.resolveFromLabel(clockRegistration.getTypeLabel()));
        entity.setColour(clockRegistration.getColour());
        entity.setManufacturer(clockRegistration.getCompany());
        ClockEntity savedClock = clockRepository.save(entity);
        return buildClockFromEntity(savedClock);
    }

    public static Clock buildClockFromEntity(ClockEntity entity) {
        return Clock.builder()
                .id(entity.getId())
                .clockType(entity.getClockType())
                .company(entity.getManufacturer())
                .colour(entity.getColour())
                .build();
    }
}