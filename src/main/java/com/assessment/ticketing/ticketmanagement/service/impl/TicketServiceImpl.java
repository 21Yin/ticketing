package com.assessment.ticketing.ticketmanagement.service.impl;

import com.assessment.ticketing.global.constants.ErrorCodeConstants;
import com.assessment.ticketing.global.document.entities.Ticket;
import com.assessment.ticketing.global.document.enumeration.TicketStatus;
import com.assessment.ticketing.global.exception.model.TicketCreationException;
import com.assessment.ticketing.global.exception.model.TicketNotFoundException;
import com.assessment.ticketing.global.utils.MessageBundle;
import com.assessment.ticketing.ticketmanagement.domain.request.CreateTicketRequest;
import com.assessment.ticketing.ticketmanagement.domain.request.UpdateTicketStatusRequest;
import com.assessment.ticketing.ticketmanagement.repository.TicketRepository;
import com.assessment.ticketing.ticketmanagement.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final MessageBundle messageBundle;

    @Override
    public Ticket createTicket(CreateTicketRequest request) {
        try {
            Ticket ticket = Ticket.builder()
                    .ticketNo("TICK-" + System.currentTimeMillis())
                    .ticketOwnerName(request.getTicketOwnerName())
                    .type(request.getType())
                    .startDate(request.getStartDate())
                    .endDate(request.getEndDate())
                    .remark(request.getRemark())
                    .status(TicketStatus.PENDING)
                    .build();
            return ticketRepository.save(ticket);
        } catch (Exception ex) {
            throw new TicketCreationException(
                    ErrorCodeConstants.ERR_TICKET_002,
                    messageBundle.getMessage(ErrorCodeConstants.ERR_TICKET_002)
            );
        }
    }

    @Override
    public Page<Ticket> getTicketsByEmployee(String ticketOwnerName, Pageable pageable) {
        return ticketRepository.findByTicketOwnerName(ticketOwnerName, pageable);
    }

    @Override
    public Page<Ticket> getPendingTickets(Pageable pageable) {
        return ticketRepository.findByStatus(TicketStatus.PENDING, pageable);
    }

    @Override
    public Page<Ticket> getApprovedTickets(Pageable pageable) {
        return ticketRepository.findByStatus(TicketStatus.APPROVED, pageable);
    }

    @Override
    public Page<Ticket> getRejectTickets(Pageable pageable) {
        return ticketRepository.findByStatus(TicketStatus.REJECTED, pageable);
    }

    @Override
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket updateStatus(Long id, UpdateTicketStatusRequest request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(
                        ErrorCodeConstants.ERR_TICKET_001,
                        messageBundle.getMessage(ErrorCodeConstants.ERR_TICKET_001)
                ));

        ticket.setStatus(request.getStatus());
        return ticketRepository.save(ticket);
    }
}
