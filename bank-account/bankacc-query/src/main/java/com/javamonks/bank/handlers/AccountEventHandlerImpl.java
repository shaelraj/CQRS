package com.javamonks.bank.handlers;

import com.javamonks.bank.events.AccountClosedEvent;
import com.javamonks.bank.events.AccountOpenedEvent;
import com.javamonks.bank.events.FundsDepositedEvent;
import com.javamonks.bank.events.FundsWithdrawnEvent;
import com.javamonks.bank.models.BankAccount;
import com.javamonks.bank.repositries.AccountRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@ProcessingGroup("bankaccount-group")
public class AccountEventHandlerImpl implements AccountEventHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountEventHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    @Override
    public void on(AccountOpenedEvent event) {
        BankAccount bankAccount = BankAccount.builder()
                .id(event.getId())
                .accountHolderId(event.getAccountHolderId())
                .creationDate(event.getCreationDate())
                .accountType(event.getAccountType())
                .balance(event.getOpeningBalance())
                .build();

        accountRepository.save(bankAccount);
    }

    @EventHandler
    @Override
    public void on(FundsDepositedEvent event) {
        Optional<BankAccount> bankAccount = accountRepository.findById(event.getId());

        if (!bankAccount.isPresent()) return;

        bankAccount.get().setBalance(event.getBalance());
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(FundsWithdrawnEvent event) {
        Optional<BankAccount> bankAccount = accountRepository.findById(event.getId());

        if (bankAccount.isPresent()) return;

        bankAccount.get().setBalance(event.getBalance());
        accountRepository.save(bankAccount.get());
    }

    @EventHandler
    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}

