package bank.strategy;

public class CurrentAccountWithdrawalStrategy implements WithdrawalStrategy {
	public boolean canWithdraw(double balance, double amount) {
		return amount <= 500 && amount <= balance;
	}
}
