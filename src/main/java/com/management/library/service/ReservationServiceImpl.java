package com.management.library.service;

import com.management.library.entity.Book;
import com.management.library.entity.Member;
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

        @Autowired
        private ReservationRepository reservationRepository;

        @Autowired
        private BookService bookService;

        @Autowired
        private MemberService memberService;

        @Override
        public List<Reservation> findAllReservations() {
            return reservationRepository.findAll();
        }

        @Override
        public List<Reservation> searchReservations(String keyword) {
            return reservationRepository.search(keyword);
        }

        @Override
        public Reservation findReservationById(Long id) {
            return reservationRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid reservation id: " + id));
        }
        @Override
        @Transactional
        public void createReservation(Reservation reservation) {
            // Récupérer le livre associé à la réservation
            Book book = bookService.findBookById(reservation.getBook().getId());
            if (book == null) {
                throw new NotFoundException("Invalid book ID");
            }

            // Définir le livre associé à la réservation
            reservation.setBook(book);

            // Récupérer le membre associé à la réservation
            Member member = memberService.findMemberById(reservation.getMember().getId());
            if (member == null) {
                throw new NotFoundException("Invalid member ID");
            }

            // Enregistrer la réservation
            reservationRepository.save(reservation);
        }
        @Override
        public void updateReservation(Reservation reservation) {
            reservationRepository.save(reservation);
        }

        @Override
        public void deleteReservation(Long id) {
            reservationRepository.deleteById(id);
        }

        @Override
        public void changeReservationStatus(Long id, String newStatus) {
            Reservation reservation = findReservationById(id);
            if ("reserved".equals(newStatus) || "render".equals(newStatus)) {
                reservation.setStatus(newStatus);
                updateReservation(reservation);
            } else {
                throw new IllegalArgumentException("Invalid reservation status: " + newStatus);
            }
        }
    }



