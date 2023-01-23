package com.bankAccount.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "action")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_action", length = 128, nullable = false)
	@JsonIgnore
	private long id;
	@Column(name = "operation", length = 128, nullable = false)
	private String operation;
	@Column(name = "date_effect", length = 128, nullable = false)
	private Date dateEffect;
	@Column(name = "amount", length = 128, nullable = false)
	private BigDecimal amount;
	@Column(name = "balance", length = 128, nullable = false)
	private BigDecimal balance;

	@ManyToOne
	@JoinColumn(name = "account_id")
	@JsonIgnore
	private Account account;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Date getDateEffect() {
		return dateEffect;
	}

	public void setDateEffect(Date dateEffect) {
		this.dateEffect = dateEffect;
	}

	

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
