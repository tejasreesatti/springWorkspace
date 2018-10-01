package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	/*@Autowired
	private BankAccountServiceImpl(BankAccountRepository bankRepository) {
		super();
		this.bankRepository = bankRepository;
	*/
	@Autowired
	private BankAccountRepository bankRepository;
	@Override
	public double getBalance(long accountId) {
		
		return bankRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		if(bankRepository.getBalance(accountId)-amount >= 0) {
			if(bankRepository.updateBalance(accountId, amount)) {
				return bankRepository.getBalance(accountId);
			}
			else {
				return bankRepository.getBalance(accountId);
			}
		
		}
		else {
			throw new LowBalanceException("You do not have sufficient balance");
		}
	}

	@Override
	public double deposit(long accountId, double amount) {
		if(bankRepository.getBalance(accountId) >= 0) {
			amount = bankRepository.getBalance(accountId) + amount;
			if(bankRepository.updateBalance(accountId, amount)) {
				return bankRepository.getBalance(accountId);
			}
			else {
				return bankRepository.getBalance(accountId);
			}
		}
		return bankRepository.getBalance(accountId);
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {
		if(bankRepository.getBalance(fromAccount) >= 0) {
			if(bankRepository.getBalance(toAccount)>= 0) {
				withdraw(fromAccount, amount);
				deposit(toAccount, amount);
				return true;
			}
		}
		return false;
	}
}
//
//	public BankAccountRepository getBankRepository() {
//		return bankRepository;
//	}
//
//	public void setBankRepository(BankAccountRepository bankRepository) {
//		this.bankRepository = bankRepository;
//	}


