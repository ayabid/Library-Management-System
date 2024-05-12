package com.management.library.service;

import com.management.library.entity.Book;
import com.management.library.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllReservations();
    List<Reservation> searchReservations(String keyword);
    Reservation findReservationById(Long id);
    void createReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    void deleteReservation(Long id);
    void changeReservationStatus(Long id, String newStatus);
}
