package bank.facade;

import bank.model.Customer;
import bank.template.CustomerLookup;
import javax.swing.*;

public class Authentication {

    // Encapsulates admin login flow
    public boolean authenticateAdmin(JFrame frame) {
        boolean loop = true, loop2 = true;

        while(loop) {
            Object adminUsername = JOptionPane.showInputDialog(frame, "Enter Administrator Username:");
            if(adminUsername == null) return false;

            if(!adminUsername.equals("admin")) {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.NO_OPTION) {
                    return false;
                }
            } else {
                loop = false;
            }
        }

        while(loop2) {
            Object adminPassword = JOptionPane.showInputDialog(frame, "Enter Administrator Password;");
            if(adminPassword == null) return false;

            if(!adminPassword.equals("admin11")) {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.NO_OPTION) {
                    return false;
                }
            } else {
                loop2 = false;
            }
        }

        return true;
    }

    public Customer authenticateCustomer(JFrame frame) {
        Customer customer = new CustomerLookup(frame, "Enter Customer ID:") {
            protected void onCancel() { }
        }.lookup();

        if(customer == null) return null;

        boolean loop2 = true;

        while(loop2) {
            Object customerPassword = JOptionPane.showInputDialog(frame, "Enter Customer Password;");
            if(customerPassword == null) return null;

            if(!customer.getPassword().equals(customerPassword)) {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.NO_OPTION) {
                    return null;
                }
            } else {
                loop2 = false;
            }
        }

        return customer;
    }
}
