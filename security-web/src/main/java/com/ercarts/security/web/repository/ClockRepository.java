package com.ercarts.security.web.repository;

import com.ercarts.security.web.dao.ClockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dkyryk
 */
@Repository
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {
}