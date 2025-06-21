package com.assessment.ticketing.ticketmanagement.repository;

import com.assessment.ticketing.global.document.entities.Ticket;
import com.assessment.ticketing.global.document.enumeration.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Page<Ticket> findByTicketOwnerName(String ticketOwnerName, Pageable pageable);
    Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);
    Page<Ticket> findAll(Pageable pageable);
}
