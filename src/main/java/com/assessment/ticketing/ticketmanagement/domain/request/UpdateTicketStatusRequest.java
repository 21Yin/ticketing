package com.assessment.ticketing.ticketmanagement.domain.request;

import com.assessment.ticketing.global.document.enumeration.TicketStatus;
import lombok.Data;

@Data
public class UpdateTicketStatusRequest {
    private TicketStatus status;
}
