package bank.command.customer;

import bank.command.Command;
import bank.model.CustomerAccount;
public class DepositCommand implements Command {
	private CustomerAccount account;
	private double amount;

	public DepositCommand(CustomerAccount account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public void execute() {
		account.setBalance(account.getBalance() + amount);
		account.addTransaction("Lodgement", amount);
	}
}
