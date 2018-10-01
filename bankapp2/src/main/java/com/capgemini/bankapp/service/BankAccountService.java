package com.capgemini.bankapp.service;

import java.util.List;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exceptions.LowBalanceException;

public interface BankAccountService {

	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount) throws LowBalanceException;
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAccount, long toAccount, double balance)throws LowBalanceException;
	public boolean addBankAccount(BankAccount account);
	public BankAccount findBankAccountById(long accountId);
	public List<BankAccount> findAllBankAccounts();
	public BankAccount updateBankAccount(BankAccount account);
	public boolean deleteBankAccount(long accountId);
	
}
