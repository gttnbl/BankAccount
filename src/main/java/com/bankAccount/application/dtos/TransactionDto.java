package com.bankAccount.application.dtos;

import java.math.BigDecimal;

public class TransactionDto {
	private String accountNo;
	private BigDecimal amount;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	

}
