package bank.factory;

import bank.model.CustomerAccount;
import bank.model.CustomerCurrentAccount;
import bank.model.ATMCard;
import bank.model.AccountTransaction;
import java.util.ArrayList;

public class CurrentAccountFactory implements AccountFactory {
	private String pin;

	public CustomerAccount createAccount(String accountNumber) {
		boolean valid = true;
		double balance = 0;
		ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
		int randomPIN = (int)(Math.random()*9000)+1000;
		pin = String.valueOf(randomPIN);
		ATMCard atm = new ATMCard(randomPIN, valid);
		return new CustomerCurrentAccount(atm, accountNumber, balance, transactionList);
	}

	public String getPin() {
		return pin;
	}

	public String getAccountPrefix() {
		return "C";
	}
}
