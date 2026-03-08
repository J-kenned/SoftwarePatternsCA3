package bank.model;

import java.util.ArrayList;
import java.util.Date;
import bank.strategy.AccountFeeStrategy;
import bank.strategy.InterestStrategy;
import bank.strategy.WithdrawalStrategy;

public class CustomerAccount  {

	String number;
	double balance;
	ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
	AccountFeeStrategy feeStrategy;
	InterestStrategy interestStrategy;
	WithdrawalStrategy withdrawalStrategy;

	//Blank Constructor
	public CustomerAccount()
	{
		this.number = "";
		this.balance = 0;
		this.transactionList = new ArrayList<AccountTransaction>();
	}
	
	//Constructor with Details
	public CustomerAccount(String number, double balance, ArrayList<AccountTransaction> transactionList)
	{
		this.number = number;
		this.balance = balance;
		this.transactionList = transactionList;
	}
	
	//Accessor methods
	
	public String getNumber()
	{
		return this.number;
	}
	
	

	
	public double getBalance()
	{
		return this.balance;
	}
	
	public ArrayList<AccountTransaction> getTransactionList()
	{
		return this.transactionList;
	}

	public void addTransaction(String type, double amount) {
		transactionList.add(new AccountTransaction(new Date().toString(), type, amount));
	}

	//Mutator methods
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
	}
	
	public void setTransactionList(ArrayList<AccountTransaction> transactionList)
	{
		this.transactionList = transactionList;
	}

	public AccountFeeStrategy getFeeStrategy()
	{
		return this.feeStrategy;
	}

	public InterestStrategy getInterestStrategy()
	{
		return this.interestStrategy;
	}

	public WithdrawalStrategy getWithdrawalStrategy()
	{
		return this.withdrawalStrategy;
	}

}
