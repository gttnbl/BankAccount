package com.bankAccount.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bankAccount.application.controller.CustomException;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 128, nullable = false)
	@JsonIgnore
	private long id;
	@Column(name = "num_account", length = 128, nullable = false)
	private String num_account;
	@Column(name = "balance", length = 128, nullable = false)
	private BigDecimal balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getNum_account() {
		return num_account;
	}

	public void setNum_account(String num_account) {
		this.num_account = num_account;
	}




	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public boolean withdrawAmount(BigDecimal withdrawalAmount) {
		if (balance.compareTo(withdrawalAmount) < 0) {
			throw new CustomException("solde insuffisant");
		}
		balance = balance.subtract(withdrawalAmount);
		return true;
	}

	public void depositAmount(BigDecimal depositAmount) {
		balance = balance.add(depositAmount);
	}


}
