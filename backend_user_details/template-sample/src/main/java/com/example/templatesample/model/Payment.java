package com.example.templatesample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document(collection = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Payment {
    @Id
    private Long paymentID;
    private String bank;
    private String accountNumber;
    private Date dateIssued;
    private String studentID;

    public Payment(String bank, String accountNumber, String studentID) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.dateIssued = new Date();
        this.studentID = studentID;
    }

}
