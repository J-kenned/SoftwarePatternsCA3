package bank.strategy;

public class CurrentAccountFeeStrategy implements AccountFeeStrategy {
	public double calculateFee() {
		return 15.0;
	}
}
