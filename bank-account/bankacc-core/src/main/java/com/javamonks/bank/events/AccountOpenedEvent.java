package com.javamonks.bank.events;

import com.javamonks.bank.models.AccountType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AccountOpenedEvent {
    private String id;
    private String accountHolderId;
    private AccountType accountType;
    private Date creationDate;
    private double openingBalance;
}
