package com.management.library.controller;

import com.management.library.entity.Reservation;
import com.management.library.entity.Book;
import com.management.library.service.MemberService;
import com.management.library.service.ReservationService;
import com.management.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    @RequestMapping("/reservations")
    public String findAllReservations(Model model) {
        List<Reservation> reservations = reservationService.findAllReservations();
        model.addAttribute("reservations", reservations);
        model.addAttribute("reservationCount", reservations.size());
        return "list-reservations";
    }


    @GetMapping("/searchReservation")
    public String searchReservation(@Param("keyword") String keyword, Model model) {
        List<Reservation> reservations = reservationService.searchReservations(keyword);
        model.addAttribute("reservations", reservations);
        model.addAttribute("keyword", keyword);
        return "list-reservations";
    }

    @RequestMapping("/reservation/{id}")
    public String findReservationById(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("reservation", reservation);
        return "list-reservation";
    }

    @GetMapping("/addReservation")
    public String showCreateReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("members", memberService.findAllMembers());
        return "add-reservation";
    }

    @PostMapping("/addReservation")
    public String createReservation(@ModelAttribute("reservation") Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "add-reservation";
        }
        // Il est possible que vous ayez besoin de mettre à jour votre service pour gérer les objets Book et Member, pas seulement leurs IDs
        reservationService.createReservation(reservation);
        return "redirect:/reservations";
    }


    @GetMapping("/updateReservation/{id}")
    public String showUpdateReservationForm(@PathVariable("id") Long id, Model model) {
        Reservation reservation = reservationService.findReservationById(id);
        model.addAttribute("reservation", reservation);
        model.addAttribute("books", bookService.findAllBooks());
        model.addAttribute("members", memberService.findAllMembers());
        return "update-reservation";
    }
    @PostMapping("/updateReservation/{id}")
    public String updateReservation(@PathVariable("id") Long id, @ModelAttribute("reservation") Reservation reservation, BindingResult result) {
        if (result.hasErrors()) {
            return "update-reservation";
        }
        reservationService.updateReservation(reservation);
        return "redirect:/reservations";
    }


    @RequestMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
    @GetMapping("/change-reservation-status/{id}")
    public String changeReservationStatus(@PathVariable("id") Long id, @RequestParam("newStatus") String newStatus) {
        try {
            reservationService.changeReservationStatus(id, newStatus);
            return "redirect:/reservations";
        } catch (IllegalArgumentException e) {
            // Si le nouveau statut fourni n'est pas valide
            // Gérer l'erreur, par exemple, rediriger vers une page d'erreur ou afficher un message d'erreur
            return "redirect:/error"; // Redirection vers une page d'erreur
        }
}}



