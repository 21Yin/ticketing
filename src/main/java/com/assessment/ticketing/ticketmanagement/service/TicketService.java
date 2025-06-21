package com.assessment.ticketing.ticketmanagement.service;

import com.assessment.ticketing.global.document.entities.Ticket;
import com.assessment.ticketing.ticketmanagement.domain.request.CreateTicketRequest;
import com.assessment.ticketing.ticketmanagement.domain.request.UpdateTicketStatusRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketService {
    Ticket createTicket(CreateTicketRequest request);
    Page<Ticket> getTicketsByEmployee(String ticketOwnerName, Pageable pageable);
    Page<Ticket> getPendingTickets(Pageable pageable);
    Page<Ticket> getApprovedTickets(Pageable pageable);
    Page<Ticket> getRejectTickets(Pageable pageable);
    Ticket updateStatus(Long id, UpdateTicketStatusRequest request);
    Page<Ticket> getAllTickets(Pageable pageable);

}
