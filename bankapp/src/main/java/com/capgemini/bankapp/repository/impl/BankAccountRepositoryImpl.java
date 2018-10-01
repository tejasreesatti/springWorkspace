package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository{


	private HashSet<BankAccount> bankAccounts;
	@Autowired
	DbUtil dbUtil;
	/*public BankAccountRepositoryImpl() {
		super();
		accounts = new HashSet<>();
		accounts.add(new BankAccount(123, "Tejasree", "SAVING", 340000));
		accounts.add(new BankAccount(124, "Swapnika", "SAVING", 440000));

	}*/
	/*@Override
	public double getBalance(long accountId) {
		
		for (BankAccount bankAccount : accounts) {
			if(bankAccount.getAccountId() == accountId) {
				return bankAccount.getAccountBalance();
			}
		}
		return -1;
		
	}*/
	
	
	public double getBalance(long accountId) 
	{
		String getBalance = "select accountbalance from springbankdata where accountId=?";
		try(Connection connection=dbUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(getBalance);) {
			preparedStatement.setInt(1, (int) accountId);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next())
			{
				return (double)result.getLong(1);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			return -1;
		}
		return -1;
	}
	
	

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		for (BankAccount bankAccount : bankAccounts) {
			if(bankAccount.getAccountId() == accountId) {
				bankAccount.setAccountBalance(newBalance);
				return true;
			}
		}
		return false;
	}



	@Override
	public boolean addBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public BankAccount findBankAccountById(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return false;
	}

}
