package bank.command.admin;

import bank.command.Command;
import bank.model.CustomerAccount;
public class ApplyChargesCommand implements Command {
	private final CustomerAccount account;
	private double feeApplied;

	public ApplyChargesCommand(CustomerAccount account) {
		this.account = account;
		this.feeApplied = 0;
	}

	public void execute() {
		feeApplied = account.getFeeStrategy().calculateFee();
		account.setBalance(account.getBalance() - feeApplied);
		account.addTransaction("Bank Charge", feeApplied);
	}

	public double getFeeApplied() {
		return feeApplied;
	}
}
