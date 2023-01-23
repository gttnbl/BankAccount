package com.bankAccount.domain.port.outgoing;

import com.bankAccount.domain.model.Account;

public interface RetrieveAccount {

    Account load(String accountNo);
}