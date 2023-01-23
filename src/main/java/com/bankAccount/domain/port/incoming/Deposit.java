package com.bankAccount.domain.port.incoming;

import com.bankAccount.application.dtos.TransactionDto;
import com.bankAccount.domain.model.Account;

public interface Deposit {

    Account deposit(TransactionDto dtos);
}