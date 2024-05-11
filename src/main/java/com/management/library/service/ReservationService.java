package com.management.library.service;

import com.management.library.entity.Book;
import com.management.library.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> findAllReservations();

    Reservation findReservationById(Long id);

    void addReservation(Long bookId, Reservation reservation);
    void updateReservation(Reservation reservation);

    void deleteReservation(Long id);

    List<Book> getAllBooks();
}
