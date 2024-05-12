package com.management.library.repository;

import com.management.library.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.status LIKE %?1%")
    List<Reservation> search(String keyword);
    List<Reservation> findByBookId(Long bookId);
}
