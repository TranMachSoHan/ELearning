package com.example.templatesample.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentDTO {
    private String bank;
    private String accountNumber;
}
