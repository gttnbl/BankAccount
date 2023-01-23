package com.bankAccount.domain.port.outgoing;

import java.util.List;

import com.bankAccount.domain.model.Account;
import com.bankAccount.domain.model.Action;

public interface PrintingAccount {
	List<Action> print(Account account);
}


