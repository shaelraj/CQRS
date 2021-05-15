package com.javamonks.bank.aggregates;

import com.javamonks.bank.commands.CloseAccountCommand;
import com.javamonks.bank.commands.DepositFundsCommand;
import com.javamonks.bank.commands.OpenAccountCommand;
import com.javamonks.bank.commands.WithdrawFundsCommand;
import com.javamonks.bank.events.AccountClosedEvent;
import com.javamonks.bank.events.AccountOpenedEvent;
import com.javamonks.bank.events.FundsDepositedEvent;
import com.javamonks.bank.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        AccountOpenedEvent event = AccountOpenedEvent.builder()
                .id(command.getId())
                .accountHolderId(command.getAccountHolderId())
                .accountType(command.getAccountType())
                .creationDate(new Date())
                .openingBalance(command.getOpeningBalance())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.getId();
        this.accountHolderId = event.getAccountHolderId();
        this.balance = event.getOpeningBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        double amount = command.getAmount();
        FundsDepositedEvent event = FundsDepositedEvent.builder()
                .id(command.getId())
                .amount(amount)
                .balance(this.balance + amount)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        this.balance += event.getAmount();
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        double amount = command.getAmount();

        if (this.balance - amount < 0) {
            throw new IllegalStateException("Withdrawal declined, insufficient funds!");
        }

        FundsWithdrawnEvent event = FundsWithdrawnEvent.builder()
                .id(command.getId())
                .amount(command.getAmount())
                .balance(this.balance - amount)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        this.balance -= event.getAmount();
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        AccountClosedEvent event = AccountClosedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}

