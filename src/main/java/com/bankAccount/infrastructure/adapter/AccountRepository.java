package com.bankAccount.infrastructure.adapter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bankAccount.domain.model.Account;
import com.bankAccount.domain.model.Action;
import com.bankAccount.domain.port.outgoing.PersistAccount;
import com.bankAccount.domain.port.outgoing.PrintingAccount;
import com.bankAccount.domain.port.outgoing.RetrieveAccount;

@Component
public class AccountRepository implements RetrieveAccount, PersistAccount, PrintingAccount {

	private SpringDataAccountRepository repository;

	public AccountRepository(SpringDataAccountRepository repository) {
		this.repository = repository;
	}

	public Account load(String accountNo) {

		return repository.findByAccountNo(accountNo);

	}

	public void save(Account bankAccount, String operation, BigDecimal amount) {
		repository.save(bankAccount, operation, amount);
	}

	public void insert(Account bankAccount) {
		repository.insert(bankAccount);
	}

	@Override
	public List<Action> print(Account account) {
		// TODO Auto-generated method stub
		return repository.print(account);
	}

}
