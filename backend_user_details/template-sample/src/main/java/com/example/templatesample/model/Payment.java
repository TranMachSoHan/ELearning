package com.example.templatesample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Payment implements Serializable {
    @Id
    private Long paymentID;

    private String name;
    private String bank;
    private String accountNumber;
    private Date dateIssued;

    public Payment(String name, String bank, String accountNumber) {
        this.name = name;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.dateIssued = new Date();
    }

    public Payment(String name, String bank, String accountNumber, Date dateIssued) {
        this.name = name;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.dateIssued = dateIssued;
    }
}
