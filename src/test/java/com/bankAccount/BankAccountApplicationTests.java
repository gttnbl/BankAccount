package com.bankAccount;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bankAccount.application.controller.CustomException;
import com.bankAccount.application.dtos.TransactionDto;
import com.bankAccount.application.enums.Operation;
import com.bankAccount.domain.model.Account;
import com.bankAccount.domain.model.Action;
import com.bankAccount.domain.port.outgoing.PersistAccount;
import com.bankAccount.domain.port.outgoing.PrintingAccount;
import com.bankAccount.domain.port.outgoing.RetrieveAccount;
import com.bankAccount.domain.service.AccountService;

@RunWith(JUnit4.class)
public class BankAccountApplicationTests {

	@InjectMocks
	AccountService accountService;

	@Mock
	RetrieveAccount retrieveAccount;
	@Mock
	PersistAccount persistAccount;
	@Mock
	PrintingAccount printingAccount;

	private TransactionDto dto;
	private String accountNo = null;
	private Account account;
	List<Action> actions;
	Action act1;
	Action act2;
	Action act3;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		accountNo = "FR00 1235478";
		dto = new TransactionDto();
		dto.setAccountNo("FR00 1235478");
		dto.setAmount(new BigDecimal(10000));
		account = new Account();
		account.setNum_account("FR00 1235478");
		account.setBalance(new BigDecimal(70000));
		actions = new ArrayList<Action>();
		act1 = new Action();
		act1.setAccount(account);
		act1.setAmount(new BigDecimal(70000));
		act1.setBalance(new BigDecimal(70000));
		act1.setDateEffect(new Date());
		act1.setOperation(Operation.DEPOSIT.getAbreviation());

		act2 = new Action();
		act2.setAccount(account);
		act2.setAmount(new BigDecimal(70000));
		act2.setBalance(new BigDecimal(70000));
		act2.setDateEffect(new Date());
		act2.setOperation(Operation.DEPOSIT.getAbreviation());

		act3 = new Action();
		act3.setAccount(account);
		act3.setAmount(new BigDecimal(70000));
		act3.setBalance(new BigDecimal(70000));
		act3.setDateEffect(new Date());
		act3.setOperation(Operation.WITHDRAW.getAbreviation());
		actions.add(act1);
		actions.add(act2);
		actions.add(act3);

	}

	@Test
	public void deposit() {

		Mockito.when(retrieveAccount.load(any())).thenReturn(account);
		doNothing().when(persistAccount).save(any(), any(), any());
		Account act = accountService.deposit(dto);
		assertEquals(new BigDecimal(80000), act.getBalance());
	}

	@Test(expected = CustomException.class)
	public void deposit_accountNotFound() {

		Mockito.when(retrieveAccount.load(any())).thenThrow(new CustomException("Account not found"));
		doNothing().when(persistAccount).save(any(), any(), any());
		Account act = accountService.deposit(dto);

	}

	@Test
	public void withdraw() {

		Mockito.when(retrieveAccount.load(any())).thenReturn(account);
		doNothing().when(persistAccount).save(any(), any(), any());
		Account act = accountService.withdraw(dto);
		assertEquals(new BigDecimal(60000), act.getBalance());
	}

	@Test(expected = CustomException.class)
	public void withdraw_accountNotFound() {

		Mockito.when(retrieveAccount.load(any())).thenThrow(new CustomException("Account not found"));
		doNothing().when(persistAccount).save(any(), any(), any());
		Account act = accountService.withdraw(dto);

	}

	@Test(expected = CustomException.class)
	public void withdraw_operationFailed() {
		account.setBalance(new BigDecimal("200"));
		Mockito.when(retrieveAccount.load(any())).thenReturn(account);
		// Mockito.when(any(Account.class).withdrawAmount(any())).thenThrow(new
		// CustomException("solde insuffisant"));
		doNothing().when(persistAccount).save(any(), any(), any());
		Account act = accountService.withdraw(dto);

	}

	@Test
	public void print() {

		Mockito.when(retrieveAccount.load(any())).thenReturn(account);

		Mockito.when(printingAccount.print(any())).thenReturn(actions);

		List<Action> acts = accountService.print(accountNo);
		assertEquals(actions.get(0).getAmount(), acts.get(0).getAmount());
		assertEquals(actions.get(1).getAmount(), acts.get(1).getAmount());
		assertEquals(actions.get(2).getAmount(), acts.get(2).getAmount());
	}
}
