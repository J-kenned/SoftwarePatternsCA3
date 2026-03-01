package bank.strategy;

public class DepositInterestStrategy implements InterestStrategy {
	public double calculateInterest(double balance, double rate) {
		return balance * (rate / 100);
	}
}
