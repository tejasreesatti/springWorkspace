package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.exceptions.LowBalanceException;

public class Application {

	public static void main(String[] args) throws LowBalanceException {
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		BankAccountController bankAccountController = context.getBean("bankAccountController", BankAccountController.class);
		

		
		System.out.println(bankAccountController.getBalance(1234));
		System.out.println(bankAccountController.fundTransfer(1234, 1121, 200));
		System.out.println(bankAccountController.withdraw(1234, 100));
		System.out.println(bankAccountController.deposit(1234, 100));
		
		
		
	}

}
