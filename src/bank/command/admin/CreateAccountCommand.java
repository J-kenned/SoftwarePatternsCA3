package bank.command.admin;

import bank.command.Command;
import bank.model.Customer;
import bank.model.CustomerAccount;
import bank.factory.AccountFactory;
import bank.factory.CurrentAccountFactory;
import bank.repository.CustomerRepository;

public class CreateAccountCommand implements Command {
	private final Customer customer;
	private final AccountFactory factory;
	private String accountNumber;
	private String pin;

	public CreateAccountCommand(Customer customer, AccountFactory factory) {
		this.customer = customer;
		this.factory = factory;
		this.accountNumber = null;
		this.pin = null;
	}

	public void execute() {
		accountNumber = String.valueOf(factory.getAccountPrefix() + (CustomerRepository.getInstance().getCustomers().indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));

		CustomerAccount account = factory.createAccount(accountNumber);
		customer.getAccounts().add(account);

		if(factory instanceof CurrentAccountFactory) {
			pin = ((CurrentAccountFactory) factory).getPin();
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getPin() {
		return pin;
	}
}
