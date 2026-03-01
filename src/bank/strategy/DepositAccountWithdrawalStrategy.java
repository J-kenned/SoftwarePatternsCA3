package bank.strategy;

public class DepositAccountWithdrawalStrategy implements WithdrawalStrategy {
	public boolean canWithdraw(double balance, double amount) {
		return amount <= balance;
	}
}
