package bank.model;

import java.util.ArrayList;
import bank.strategy.CurrentAccountFeeStrategy;
import bank.strategy.NoInterestStrategy;
import bank.strategy.CurrentAccountWithdrawalStrategy;

public class CustomerCurrentAccount extends CustomerAccount
{
	ATMCard atm;

public CustomerCurrentAccount()
{
	super();
	this.atm = null;
	this.feeStrategy = new CurrentAccountFeeStrategy();
	this.interestStrategy = new NoInterestStrategy();
	this.withdrawalStrategy = new CurrentAccountWithdrawalStrategy();
}

public CustomerCurrentAccount(ATMCard atm, String number, double balance, ArrayList<AccountTransaction> transactionList)
{
	super(number, balance, transactionList);
	this.atm = atm;
	this.feeStrategy = new CurrentAccountFeeStrategy();
	this.interestStrategy = new NoInterestStrategy();
	this.withdrawalStrategy = new CurrentAccountWithdrawalStrategy();
}

public ATMCard getAtm()
{
	return this.atm;
}

public void setAtm(ATMCard atm)
{
	this.atm = atm;
}

}