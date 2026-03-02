package bank.template;

import bank.repository.CustomerRepository;
import bank.model.Customer;
import javax.swing.*;

public abstract class CustomerLookup {
    private final JFrame frame;
    private final String prompt;

    public CustomerLookup(JFrame frame, String prompt) {
        this.frame = frame;
        this.prompt = prompt;
    }

    public final Customer lookup() {
        while(true) {
            String customerID = JOptionPane.showInputDialog(frame, prompt);
            if(customerID == null) { onCancel(); return null; }

            Customer customer = CustomerRepository.getInstance()
                .findCustomerById(customerID);
            if(customer != null) {
                return customer;
            }

            int reply = JOptionPane.showConfirmDialog(null, null,
                "User not found. Try again?", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.NO_OPTION) {
                onCancel();
                return null;
            }
        }
    }

    protected abstract void onCancel();
}
