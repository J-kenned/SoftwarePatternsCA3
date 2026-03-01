package bank.command.customer;

import bank.command.Command;
import bank.model.CustomerAccount;
import bank.model.AccountTransaction;
import java.util.Date;

public class DepositCommand implements Command {
	private CustomerAccount account;
	private double amount;

	public DepositCommand(CustomerAccount account, double amount) {
		this.account = account;
		this.amount = amount;
	}

	public void execute() {
		account.setBalance(account.getBalance() + amount);
		Date date = new Date();
		String date2 = date.toString();
		String type = "Lodgement";
		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		account.getTransactionList().add(transaction);
	}
}
