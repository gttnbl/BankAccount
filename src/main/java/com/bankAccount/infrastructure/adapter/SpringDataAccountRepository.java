package com.bankAccount.infrastructure.adapter;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.bankAccount.domain.model.Account;
import com.bankAccount.domain.model.Action;

@Repository
public class SpringDataAccountRepository extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public Account findByAccountNo(String accountNo) {
		String sql = "SELECT * FROM account WHERE num_account = ?";
		return (Account) getJdbcTemplate().queryForObject(sql, new Object[] { accountNo }, new RowMapper<Account>() {
			public Account mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Account account = new Account();
				account.setId(rs.getLong("id"));
				account.setNum_account(rs.getString("num_account"));
				account.setBalance(rs.getBigDecimal("balance"));
				return account;
			}
		});
	}

	public void save(Account bankAccount, String operation, BigDecimal amount) {
		String sql = "UPDATE account SET " + "balance= ? WHERE num_account = ?";
		getJdbcTemplate().update(sql, new Object[] { bankAccount.getBalance(), bankAccount.getNum_account() });

		addAction(bankAccount, operation, amount);
	}

	public void insert(Account bankAccount) {
		String sql = "INSERT INTO  account (id,num_account,balance) VALUES(?,?,?)";

		getJdbcTemplate().update(sql,
				new Object[] { bankAccount.getId(), bankAccount.getNum_account(), bankAccount.getBalance() });
	}

	public List<Action> print(Account account) {
		// TODO Auto-generated method stub
		String sql = "select operation,date_effect ,amount,balance from action where account_id = " + account.getId();
		List<Action> actions = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Action>(Action.class));
		return actions;

	}

	public void addAction(Account bankAccount, String operation, BigDecimal amount) {

		final SqlRowSet sqlRowSet = getJdbcTemplate().queryForRowSet("select MAX(id_action) from ACTION ");
		sqlRowSet.next();// mandatory to move the cursor
		long nextVal = sqlRowSet.getLong(1);

		String sql = "INSERT INTO  action(id_action,operation,date_effect,amount,balance,account_id)   VALUES(?,?,?,?,?,?)";

		getJdbcTemplate().update(sql, new Object[] { nextVal + 1, operation, new Date(), amount,
				bankAccount.getBalance(), bankAccount.getId() });
	}

}
