package com.bankAccount.domain.service;

import java.util.List;
import com.bankAccount.application.controller.CustomException;
import com.bankAccount.application.dtos.TransactionDto;
import com.bankAccount.application.enums.Operation;
import com.bankAccount.domain.model.Account;
import com.bankAccount.domain.model.Action;
import com.bankAccount.domain.port.incoming.Deposit;
import com.bankAccount.domain.port.incoming.Printing;
import com.bankAccount.domain.port.incoming.Withdraw;
import com.bankAccount.domain.port.outgoing.PersistAccount;
import com.bankAccount.domain.port.outgoing.PrintingAccount;
import com.bankAccount.domain.port.outgoing.RetrieveAccount;

public class AccountService implements Deposit, Withdraw, Printing {

	private RetrieveAccount retrieveAccount;
	private PersistAccount persistAccount;
	private PrintingAccount printingAccount;

	public AccountService(PersistAccount persistAccount, RetrieveAccount retrieveAccount,
			PrintingAccount printingAccount) {

		this.retrieveAccount = retrieveAccount;
		this.persistAccount = persistAccount;
		this.printingAccount = printingAccount;

	}

	public Account deposit(TransactionDto dtos) {

		Operation op = Operation.DEPOSIT;
		String operat = op.getAbreviation();
		Account account;
		try {
			account = retrieveAccount.load(dtos.getAccountNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block

			throw new CustomException("Account not found");
		}
		account.depositAmount(dtos.getAmount());
		persistAccount.save(account, operat, dtos.getAmount());
		return account;
	}

	public Account withdraw(TransactionDto dtos) {

		Operation op = Operation.WITHDRAW;

		String operat = op.getAbreviation();
		Account account;
		try {
			account = retrieveAccount.load(dtos.getAccountNo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException("Account not found");
		}
		try {
			account.withdrawAmount(dtos.getAmount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CustomException(e.getMessage());
		}
		persistAccount.save(account, operat, dtos.getAmount());
		return account;
	}

	public void save(Account account) {
		persistAccount.insert(account);
	}

	@Override
	public List<Action> print(String accountNo) {
		// TODO Auto-generated method stub
		Account account = retrieveAccount.load(accountNo);
		return printingAccount.print(account);

	}
}