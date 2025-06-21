package com.assessment.ticketing.ticketmanagement.controller;

import com.assessment.ticketing.global.BaseController;
import com.assessment.ticketing.global.GlobalResponse;
import com.assessment.ticketing.global.constants.SuccessCodeConstants;
import com.assessment.ticketing.global.document.entities.Ticket;
import com.assessment.ticketing.global.utils.MessageBundle;
import com.assessment.ticketing.ticketmanagement.domain.request.CreateTicketRequest;
import com.assessment.ticketing.ticketmanagement.domain.request.UpdateTicketStatusRequest;
import com.assessment.ticketing.ticketmanagement.domain.response.CreateTicketResponse;
import com.assessment.ticketing.ticketmanagement.domain.response.TicketListResponse;
import com.assessment.ticketing.ticketmanagement.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController extends BaseController {

    private final TicketService ticketService;
    private final MessageBundle messageBundle;
    @PostMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<GlobalResponse<CreateTicketResponse>> createTicket(
            @RequestBody CreateTicketRequest request, Authentication auth) {

        request.setTicketOwnerName(auth.getName());
        Ticket ticket = ticketService.createTicket(request);

        return createResponse(
                HttpStatus.CREATED,
                mapToResponse(ticket),
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<GlobalResponse<Page<TicketListResponse>>> getMyTickets(
            Authentication auth,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<TicketListResponse> list = ticketService.getTicketsByEmployee(
                auth.getName(), pageable)
                .map(this::mapToListResponse);

        return createResponse(
                HttpStatus.OK,
                list,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }
    @GetMapping("/PENDING")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Page<TicketListResponse>>> getPendingTickets(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<TicketListResponse> responsePage = ticketService.getPendingTickets(pageable)
                .map(this::mapToListResponse);

        return createResponse(
                HttpStatus.OK,
                responsePage,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }

    @GetMapping("/APPROVED")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Page<TicketListResponse>>> getApprovedTickets(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<TicketListResponse> responsePage = ticketService.getApprovedTickets(pageable)
                .map(this::mapToListResponse);

        return createResponse(
                HttpStatus.OK,
                responsePage,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }

    @GetMapping("/REJECTED")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Page<TicketListResponse>>> getRejectTickets(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<TicketListResponse> responsePage = ticketService.getRejectTickets(pageable)
                .map(this::mapToListResponse);

        return createResponse(
                HttpStatus.OK,
                responsePage,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<Page<TicketListResponse>>> getAllTickets(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<TicketListResponse> responsePage = ticketService.getAllTickets(pageable)
                .map(this::mapToListResponse);

        return createResponse(
                HttpStatus.OK,
                responsePage,
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }


    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GlobalResponse<TicketListResponse>> updateStatus(
            @PathVariable Long id, @RequestBody UpdateTicketStatusRequest request) {

        Ticket updated = ticketService.updateStatus(id, request);

        return createResponse(
                HttpStatus.OK,
                mapToListResponse(updated),
                messageBundle.getMessage(SuccessCodeConstants.SUC_COM001)
        );
    }

    private CreateTicketResponse mapToResponse(Ticket ticket) {
        return CreateTicketResponse.builder()
                .id(ticket.getId())
                .ticketNo(ticket.getTicketNo())
                .ticketOwnerName(ticket.getTicketOwnerName())
                .type(ticket.getType())
                .startDate(ticket.getStartDate())
                .endDate(ticket.getEndDate())
                .remark(ticket.getRemark())
                .status(ticket.getStatus())
                .build();
    }

    private TicketListResponse mapToListResponse(Ticket ticket) {
        return TicketListResponse.builder()
                .id(ticket.getId())
                .ticketNo(ticket.getTicketNo())
                .ticketOwnerName(ticket.getTicketOwnerName())
                .type(ticket.getType())
                .startDate(ticket.getStartDate())
                .endDate(ticket.getEndDate())
                .remark(ticket.getRemark())
                .status(ticket.getStatus())
                .build();
    }
}
