package com.mik;


import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable{
	String AccountNumber;
	double amount;
	double loan;
	String password;
	Person person;
	
	public Account ()
	{
		
	}
	
	public Account (String accountNumber, double amount, double loan, String password)
	{
		this.AccountNumber = accountNumber;
		this.amount = amount;
		this.loan = loan;
		this.password = password;
	}
	
	public void Deposit (double amount)
	{
		this.amount += amount;
	}
	
	public void withdraw (double amount)
	{
		this.amount -= amount;
	}
	
	public void Loan (double amount)
	{
		loan = amount;
	}
	
	public double getAmount()
	{
		return amount;
	}

}
