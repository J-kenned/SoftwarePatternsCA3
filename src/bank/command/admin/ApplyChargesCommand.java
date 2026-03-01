package bank.command.admin;

import bank.command.Command;
import bank.model.CustomerAccount;
import bank.model.CustomerCurrentAccount;
import bank.model.CustomerDepositAccount;
import bank.model.AccountTransaction;
import java.util.Date;

public class ApplyChargesCommand implements Command {
	private final CustomerAccount account;
	private double feeApplied;

	public ApplyChargesCommand(CustomerAccount account) {
		this.account = account;
		this.feeApplied = 0;
	}

	public void execute() {
		if(account instanceof CustomerDepositAccount) {
			feeApplied = 25;
		}
		if(account instanceof CustomerCurrentAccount) {
			feeApplied = 15;
		}
		account.setBalance(account.getBalance() - feeApplied);
		Date date = new Date();
		String date2 = date.toString();
		AccountTransaction transaction = new AccountTransaction(date2, "Bank Charge", feeApplied);
		account.getTransactionList().add(transaction);
	}

	public double getFeeApplied() {
		return feeApplied;
	}
}
