package com.assessment.ticketing.global.document;

import lombok.Generated;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Indexed;

import java.time.LocalDateTime;

public class BaseDocument {
        @CreatedDate
        private LocalDateTime createdDate;
        @CreatedBy
        private String createdBy;
        @LastModifiedDate
        private LocalDateTime updatedDate;
        @LastModifiedBy
        private String updatedBy;

}
