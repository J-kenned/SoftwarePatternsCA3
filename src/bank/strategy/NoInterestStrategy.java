package bank.strategy;

public class NoInterestStrategy implements InterestStrategy {
	public double calculateInterest(double balance, double rate) {
		return 0;
	}
}
