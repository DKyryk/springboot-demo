package com.ercarts.springboot.demo.web.data;

import com.ercarts.springboot.demo.web.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dkyryk
 */
public interface RoomRepository extends JpaRepository<Room, Long> {
}
