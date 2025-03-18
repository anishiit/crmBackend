package com.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDto {
    private Long id;
    private String question;
    private String answer;
    private String category;
    private Integer views;
    private LocalDateTime createdAt;
}

