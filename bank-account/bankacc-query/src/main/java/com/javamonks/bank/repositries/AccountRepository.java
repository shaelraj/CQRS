package com.javamonks.bank.repositries;

import com.javamonks.bank.models.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<BankAccount, String> {
}

