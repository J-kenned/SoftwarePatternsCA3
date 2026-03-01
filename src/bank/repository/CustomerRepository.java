package bank.repository;

import bank.model.Customer;
import java.util.ArrayList;

public class CustomerRepository {
	private static CustomerRepository instance;
	private final ArrayList<Customer> customerList;

	private CustomerRepository() {
		customerList = new ArrayList<Customer>();
	}

	public static CustomerRepository getInstance() {
		if(instance == null) {
			instance = new CustomerRepository();
		}
		return instance;
	}

	public ArrayList<Customer> getCustomers() {
		return customerList;
	}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public void removeCustomer(Customer customer) {
		customerList.remove(customer);
	}

	public Customer findCustomerById(String customerID) {
		for(Customer customer : customerList) {
			if(customer.getCustomerID().equals(customerID)) {
				return customer;
			}
		}
		return null;
	}

	public boolean isEmpty() {
		return customerList.isEmpty();
	}

	public int size() {
		return customerList.size();
	}
}
