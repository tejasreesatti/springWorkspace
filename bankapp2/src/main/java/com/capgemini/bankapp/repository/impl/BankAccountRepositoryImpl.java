package com.capgemini.bankapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
	long balance =jdbcTemplate.queryForObject("SELECT accountBalance FROM springbankdata WHERE accountid = ?", new Object[] {accountId}, long.class);
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("UPDATE springbankdata SET accountBalance = ? WHERE accountid =?", new Object[] {accountId, newBalance});
		
		
		return count != 0;
	}
	

	@Override
	public boolean addBankAccount(BankAccount account) {
		jdbcTemplate.update("INSERT into springbankdata VALUES(?,?,?,?)", new Object[] {account.getAccountId(), account.getAccountHolderName(), account.getAccountType(), account.getAccountBalance()}, Object.class);
		return false;
	}



	@Override
	public BankAccount findBankAccountById(long accountId) {
		return jdbcTemplate.queryForObject("SELECT * FROM springbankdata WHERE accountid = ?", new Object[]{accountId}, new BankAccountRowMapper());
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		return	jdbcTemplate.query("SELECT * FROM springbankdata", new Object[] {} , new BankAccountRowMapper());
		 
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("UPDATE springbankdata SET accountholdername = ?, accountType = ? WHERE accountid = ?", new Object[] {account.getAccountHolderName(), account.getAccountType(), account.getAccountId()});
		
		return count != 0 ? account : findBankAccountById(account.getAccountId()) ;
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		int count = jdbcTemplate.update("DELETE FROM springbankdata WHERE accountid = ?", new Object[] {accountId});
		return count !=  0;
	}

	private class BankAccountRowMapper implements RowMapper<BankAccount> {
		@Override
		public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException{
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt(1));
			account.setAccountHolderName(rs.getString(2));
			account.setAccountType(rs.getString(3));
			account.setAccountBalance(rs.getLong(4));
			return account;
			
		}
	}

}
