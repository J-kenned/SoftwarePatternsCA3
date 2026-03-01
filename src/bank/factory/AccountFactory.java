package bank.factory;

import bank.model.CustomerAccount;

public interface AccountFactory {
	CustomerAccount createAccount(String accountNumber);
	String getAccountPrefix();
}
