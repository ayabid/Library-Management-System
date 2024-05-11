package com.management.library.controller;

import com.management.library.entity.Reservation;
import com.management.library.entity.Book;
import com.management.library.service.ReservationService;
import com.management.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final BookService bookService;

    @Autowired
    public ReservationController(ReservationService reservationService, BookService bookService) {
        this.reservationService = reservationService;
        this.bookService = bookService;
    }

    @GetMapping("/reservations")
    public String findAllReservations(Model model) {
        List<Reservation> reservations = reservationService.findAllReservations();
        model.addAttribute("reservations", reservations);
        return "list-reservations";
    }

    @GetMapping("/add-reservation")
    public String showAddReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("books", bookService.findAllBooks());
        return "add-reservation";
    }
    @PostMapping("/add-reservation")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation, Model model) {
        Long bookId = reservation.getBook().getId(); // Auparavant, on recevait l'objet complet "Livre" via reservation.getBook().
        // La nouvelle méthode attend uniquement l'ID du livre. On récupère donc l'ID du livre sélectionné.
        reservationService.addReservation(bookId, reservation); // On passe l'ID du livre et l'objet réservation au service.
        return "redirect:/reservations";
    }


    @GetMapping("/updatereservation/{id}")
    public String showUpdateReservationForm(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("books", bookService.findAllBooks());
        return "update-reservation";
    }

    @PostMapping("/updatereservation/{id}")
    public String updateReservation(@PathVariable("id") Long id, @ModelAttribute("reservation") Reservation reservation, Model model) {
        reservationService.updateReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/removereservation/{id}")
    public String deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}
