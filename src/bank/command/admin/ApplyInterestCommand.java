package bank.command.admin;

import bank.command.Command;
import bank.model.CustomerAccount;
import bank.model.AccountTransaction;
import java.util.Date;

public class ApplyInterestCommand implements Command {
	private final CustomerAccount account;
	private final double rate;
	private double interestApplied;

	public ApplyInterestCommand(CustomerAccount account, double rate) {
		this.account = account;
		this.rate = rate;
		this.interestApplied = 0;
	}

	public void execute() {
		interestApplied = account.getBalance() * (rate / 100);
		account.setBalance(account.getBalance() + interestApplied);
		Date date = new Date();
		String date2 = date.toString();
		AccountTransaction transaction = new AccountTransaction(date2, "Interest", interestApplied);
		account.getTransactionList().add(transaction);
	}

	public double getInterestApplied() {
		return interestApplied;
	}
}
