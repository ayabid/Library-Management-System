package com.management.library.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "client_code", length = 50, nullable = false)
    private String clientCode;

    @Column(name = "client_name", length = 100, nullable = false)
    private String clientName;

    @Column(name = "client_phone", length = 20, nullable = false)
    private String clientPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


    @Column(name = "reservation_date", nullable = false)
    private Date reservationDate;


    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // Statut de la réservation : réservé ou rendu

    @Transient
    private String bookName;
    public String getBookName() {
        return book != null ? book.getName() : null;
    }


}