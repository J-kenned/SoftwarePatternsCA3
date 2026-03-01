package bank.strategy;

public class DepositAccountFeeStrategy implements AccountFeeStrategy {
	public double calculateFee() {
		return 25.0;
	}
}
