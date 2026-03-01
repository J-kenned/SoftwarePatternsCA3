package bank.model;

import java.util.ArrayList;
import bank.strategy.DepositAccountFeeStrategy;
import bank.strategy.DepositInterestStrategy;
import bank.strategy.DepositAccountWithdrawalStrategy;

public class CustomerDepositAccount extends CustomerAccount
{
   double interestRate;

public CustomerDepositAccount()
{
	super();
	this.interestRate = 0;
	this.feeStrategy = new DepositAccountFeeStrategy();
	this.interestStrategy = new DepositInterestStrategy();
	this.withdrawalStrategy = new DepositAccountWithdrawalStrategy();
}

public CustomerDepositAccount(double interestRate, String number, double balance, ArrayList<AccountTransaction> transactionList)
{
	super(number, balance, transactionList);
	this.interestRate = interestRate;
	this.feeStrategy = new DepositAccountFeeStrategy();
	this.interestStrategy = new DepositInterestStrategy();
	this.withdrawalStrategy = new DepositAccountWithdrawalStrategy();
}

public double getInterestRate()
{
	return this.interestRate;
}

public void setInterestRate(double interestRate)
{
	this.interestRate = interestRate;
}



}