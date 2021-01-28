package com.ercarts.security.web.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ercarts.security.web.dao.ClockEntity;
import com.ercarts.security.web.domain.Clock;
import com.ercarts.security.web.domain.ClockType;
import com.ercarts.security.web.error.ClockNotFoundException;
import com.ercarts.security.web.model.ClockRegistration;
import com.ercarts.security.web.repository.ClockRepository;
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
                .orElseThrow(() -> new ClockNotFoundException("Clock with id is not registered: " + id));
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