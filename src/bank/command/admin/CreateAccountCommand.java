package bank.command.admin;

import bank.command.Command;
import bank.model.Customer;
import bank.model.CustomerAccount;
import bank.model.CustomerCurrentAccount;
import bank.model.CustomerDepositAccount;
import bank.model.ATMCard;
import bank.model.AccountTransaction;
import java.util.ArrayList;

public class CreateAccountCommand implements Command {
	private final Customer customer;
	private final String accountType;
	private final ArrayList<Customer> customerList;
	private String accountNumber;
	private String pin;

	public CreateAccountCommand(Customer customer, String accountType, ArrayList<Customer> customerList) {
		this.customer = customer;
		this.accountType = accountType;
		this.customerList = customerList;
		this.accountNumber = null;
		this.pin = null;
	}

	public void execute() {
		if(accountType.equals("Current Account")) {
			boolean valid = true;
			double balance = 0;
			accountNumber = String.valueOf("C" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			int randomPIN = (int)(Math.random()*9000)+1000;
			pin = String.valueOf(randomPIN);
			ATMCard atm = new ATMCard(randomPIN, valid);
			CustomerAccount current = new CustomerCurrentAccount(atm, accountNumber, balance, transactionList);
			customer.getAccounts().add(current);
		}

		if(accountType.equals("Deposit Account")) {
			double balance = 0, interest = 0;
			accountNumber = String.valueOf("D" + (customerList.indexOf(customer)+1) * 10 + (customer.getAccounts().size()+1));
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			CustomerAccount deposit = new CustomerDepositAccount(interest, accountNumber, balance, transactionList);
			customer.getAccounts().add(deposit);
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getPin() {
		return pin;
	}
}
