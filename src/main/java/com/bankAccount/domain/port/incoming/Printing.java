package com.bankAccount.domain.port.incoming;

import java.util.List;

import com.bankAccount.domain.model.Action;

public interface Printing {
	 List<Action> print(String accountNo);
}
