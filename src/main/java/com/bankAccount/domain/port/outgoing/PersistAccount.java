package com.bankAccount.domain.port.outgoing;

import java.math.BigDecimal;

import com.bankAccount.domain.model.Account;

public interface PersistAccount {

    void save(Account account,String oper,BigDecimal amount);
    void insert(Account account);
}