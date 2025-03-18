package com.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String assignedTo;
    private String customer;
    private LocalDateTime createdAt;
}

