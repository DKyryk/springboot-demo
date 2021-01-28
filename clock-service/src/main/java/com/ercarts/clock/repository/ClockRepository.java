package com.ercarts.clock.repository;

import com.ercarts.clock.dao.ClockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dkyryk
 */
@Repository
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {
}