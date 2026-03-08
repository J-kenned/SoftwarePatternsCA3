package bank.command.customer;

import bank.command.Command;
import bank.model.CustomerAccount;
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
		if(!account.getWithdrawalStrategy().canWithdraw(account.getBalance(), amount)) {
			if(amount > 500) {
				resultMessage = "500 is the maximum you can withdraw at a time.";
			} else {
				resultMessage = "Insufficient funds.";
			}
			return;
		}
		account.setBalance(account.getBalance() - amount);
		account.addTransaction("Withdraw", amount);
	}

	public String getResultMessage() {
		return resultMessage;
	}
}
