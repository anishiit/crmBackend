package com.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String company;
    private String status;
    private LocalDateTime createdAt;
}

