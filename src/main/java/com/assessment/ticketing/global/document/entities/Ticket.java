package com.assessment.ticketing.global.document.entities;

import com.assessment.ticketing.global.document.BaseDocument;
import com.assessment.ticketing.global.document.enumeration.TicketStatus;
import com.assessment.ticketing.global.document.enumeration.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket extends BaseDocument {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(unique=true, nullable=false)
        private String ticketNo;

        private String ticketOwnerName;

        @Enumerated(EnumType.STRING)
        private TicketType type;

        private LocalDate startDate;
        private LocalDate endDate;

        private String remark;

        @Enumerated(EnumType.STRING)
        private TicketStatus status;

}
