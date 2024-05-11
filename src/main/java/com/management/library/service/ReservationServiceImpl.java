package com.management.library.service;

import com.management.library.entity.Book;
import com.management.library.entity.Reservation;
import com.management.library.exception.NotFoundException;
import com.management.library.repository.BookRepository;
import com.management.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Reservation findReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found with ID: " + id));
    }

    @Override
    public void addReservation(Long bookId, Reservation reservation) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book not found with ID: " + bookId));
        reservation.setBook(book);
        reservationRepository.save(reservation);
    }


    @Override
    public void updateReservation(Reservation reservation) {
        Reservation existingReservation = findReservationById(reservation.getId());
        existingReservation.setClientCode(reservation.getClientCode());
        existingReservation.setClientName(reservation.getClientName());
        existingReservation.setClientPhone(reservation.getClientPhone());
        existingReservation.setReservationDate(reservation.getReservationDate());
        existingReservation.setReturnDate(reservation.getReturnDate());
        existingReservation.setStatus(reservation.getStatus());
        reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
