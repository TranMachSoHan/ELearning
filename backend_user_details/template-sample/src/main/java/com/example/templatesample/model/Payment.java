package com.example.templatesample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Payment {
    @Id
    private Long paymentID;

    private String name;
    private String bank;
    private String accountNumber;
    private Date dateIssued;
    private String studentID;

    public Payment(String name, String bank, String accountNumber, String studentID) {
        this.name = name;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.dateIssued = new Date();
        this.studentID = studentID;
    }

}
