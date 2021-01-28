package com.ercarts.clock.repository;

import com.ercarts.clock.dao.ClockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dkyryk
 */
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {
}