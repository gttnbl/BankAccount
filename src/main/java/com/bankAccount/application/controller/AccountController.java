package com.bankAccount.application.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bankAccount.application.dtos.TransactionDto;
import com.bankAccount.domain.model.Action;
import com.bankAccount.domain.port.incoming.Deposit;
import com.bankAccount.domain.port.incoming.Printing;
import com.bankAccount.domain.port.incoming.Withdraw;

@RestController
@RequestMapping("/account")
public class AccountController {

	private final Deposit depositUseCase;
	private final Withdraw withdrawUseCase;

	private final Printing printingUseCase;

	public AccountController(Deposit depositUseCase, Withdraw withdrawUseCase, Printing printingUseCase) {
		this.depositUseCase = depositUseCase;
		this.withdrawUseCase = withdrawUseCase;
		this.printingUseCase = printingUseCase;
	}


	@PostMapping("/deposit")
	public ResponseEntity<String> deposit(TransactionDto dtos) {
		try {
			depositUseCase.deposit(dtos);
			return new ResponseEntity<>(
					"Deposit sucess for the amount " + dtos.getAmount() + " in the account " + dtos.getAccountNo(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<String> withdraw(TransactionDto dtos) {

		try {
			 withdrawUseCase.withdraw(dtos);
			return new ResponseEntity<>(
					"withdraw sucess for the amount " + dtos.getAmount() + " from the account " + dtos.getAccountNo(),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping(value = "/{accountNo}/print")
	public List<Action> print(@PathVariable final String accountNo) {
		return printingUseCase.print(accountNo);
	}

}
