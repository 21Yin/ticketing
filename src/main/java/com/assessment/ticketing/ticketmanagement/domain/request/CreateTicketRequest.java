package com.assessment.ticketing.ticketmanagement.domain.request;


import com.assessment.ticketing.global.document.enumeration.TicketType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTicketRequest {
    private TicketType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remark;
    private String ticketOwnerName;
}
