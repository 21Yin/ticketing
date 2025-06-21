package com.assessment.ticketing.ticketmanagement.domain.response;

import com.assessment.ticketing.global.document.BaseDocument;
import com.assessment.ticketing.global.document.enumeration.TicketStatus;
import com.assessment.ticketing.global.document.enumeration.TicketType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TicketListResponse extends BaseDocument {
    private int id;
    private String ticketNo;
    private String ticketOwnerName;
    private TicketType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String remark;
    private TicketStatus status;
}
