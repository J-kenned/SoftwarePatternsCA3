package bank.command;

import bank.model.CustomerAccount;
import bank.model.CustomerCurrentAccount;
import bank.model.AccountTransaction;
import java.util.Date;

public class WithdrawCommand implements Command {
	private final CustomerAccount account;
	private final double amount;
	private String resultMessage;

	public WithdrawCommand(CustomerAccount account, double amount) {
		this.account = account;
		this.amount = amount;
		this.resultMessage = null;
	}

	public void execute() {
		if(account instanceof CustomerCurrentAccount) {
			if(amount > 500) {
				resultMessage = "500 is the maximum you can withdraw at a time.";
				return;
			}
		}
		if(amount > account.getBalance()) {
			resultMessage = "Insufficient funds.";
			return;
		}
		account.setBalance(account.getBalance() - amount);
		Date date = new Date();
		String date2 = date.toString();
		String type = "Withdraw";
		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		account.getTransactionList().add(transaction);
	}

	public String getResultMessage() {
		return resultMessage;
	}
}
