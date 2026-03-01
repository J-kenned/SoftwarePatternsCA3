package bank.factory;

import bank.model.CustomerAccount;
import bank.model.CustomerDepositAccount;
import bank.model.AccountTransaction;
import java.util.ArrayList;

public class DepositAccountFactory implements AccountFactory {
	public String getAccountPrefix() {
		return "D";
	}

	public CustomerAccount createAccount(String accountNumber) {
		double balance = 0, interest = 0;
		ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
		return new CustomerDepositAccount(interest, accountNumber, balance, transactionList);
	}
}
