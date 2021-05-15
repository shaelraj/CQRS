package com.javamonks.bank.handlers;

import com.javamonks.bank.events.AccountClosedEvent;
import com.javamonks.bank.events.AccountOpenedEvent;
import com.javamonks.bank.events.FundsDepositedEvent;
import com.javamonks.bank.events.FundsWithdrawnEvent;

public interface AccountEventHandler {
    void on(AccountOpenedEvent event);
    void on(FundsDepositedEvent event);
    void on(FundsWithdrawnEvent event);
    void on(AccountClosedEvent event);
}
