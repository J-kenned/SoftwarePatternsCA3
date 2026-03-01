package bank.command.admin;

import bank.command.Command;
import bank.model.Customer;
import java.util.ArrayList;

public class DeleteCustomerCommand implements Command {
	private final Customer customer;
	private final ArrayList<Customer> customerList;
	private String resultMessage;
	private boolean success;

	public DeleteCustomerCommand(Customer customer, ArrayList<Customer> customerList) {
		this.customer = customer;
		this.customerList = customerList;
		this.resultMessage = null;
		this.success = false;
	}

	public void execute() {
		if(customer.getAccounts().size() > 0) {
			resultMessage = "This customer has accounts. \n You must delete a customer's accounts before deleting a customer ";
			success = false;
		}
		else {
			customerList.remove(customer);
			resultMessage = "Customer Deleted ";
			success = true;
		}
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public boolean isSuccess() {
		return success;
	}
}
