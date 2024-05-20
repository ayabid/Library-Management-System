package com.management.library.controller;

import com.management.library.entity.Reservation;
import com.management.library.service.BookService;
import com.management.library.service.MemberService;
import com.management.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        int memberCount = memberService.findAllMembers().size();
        model.addAttribute("memberCount", memberCount);
        int reservationCount = reservationService.findAllReservations().size();
        model.addAttribute("reservationCount", reservationCount);
        int bookCount = bookService.findAllBooks().size();
        model.addAttribute("bookCount", bookCount);
        List<Reservation> reservations = reservationService.findAllReservations();

        // Ajouter les réservations au modèle de la page index
        model.addAttribute("reservations", reservations);

        return "index";
}}