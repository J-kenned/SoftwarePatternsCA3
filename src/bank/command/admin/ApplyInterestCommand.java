package bank.command.admin;

import bank.command.Command;
import bank.model.CustomerAccount;
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
		interestApplied = account.getInterestStrategy().calculateInterest(account.getBalance(), rate);
		account.setBalance(account.getBalance() + interestApplied);
		account.addTransaction("Interest", interestApplied);
	}

	public double getInterestApplied() {
		return interestApplied;
	}
}
