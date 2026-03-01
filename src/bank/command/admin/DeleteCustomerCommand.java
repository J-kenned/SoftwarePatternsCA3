package bank.command.admin;

import bank.command.Command;
import bank.model.Customer;
import bank.repository.CustomerRepository;

public class DeleteCustomerCommand implements Command {
	private final Customer customer;
	private String resultMessage;
	private boolean success;

	public DeleteCustomerCommand(Customer customer) {
		this.customer = customer;
		this.resultMessage = null;
		this.success = false;
	}

	public void execute() {
		if(!customer.getAccounts().isEmpty()) {
			resultMessage = "This customer has accounts. \n You must delete a customer's accounts before deleting a customer ";
			success = false;
		}
		else {
			CustomerRepository.getInstance().removeCustomer(customer);
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
