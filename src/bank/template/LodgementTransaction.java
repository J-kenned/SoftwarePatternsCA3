package bank.template;

import bank.model.CustomerAccount;
import bank.command.Command;
import bank.command.CommandInvoker;
import bank.command.customer.DepositCommand;
import javax.swing.*;

public class LodgementTransaction extends AuthenticatedTransaction {
    private final Runnable onLockoutCallback;

    public LodgementTransaction(JFrame frame, CustomerAccount account,
                                CommandInvoker invoker, Runnable onLockoutCallback) {
        super(frame, account, invoker);
        this.onLockoutCallback = onLockoutCallback;
    }

    protected void onLockout() {
        onLockoutCallback.run();
    }

    protected double requestAmount() {
        String balanceTest = JOptionPane.showInputDialog(frame, "Enter amount you wish to lodge:");
        if(isNumeric(balanceTest)) {
            return Double.parseDouble(balanceTest);
        } else {
            JOptionPane.showMessageDialog(frame, "You must enter a numerical value!",
                "Oops!", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
    }

    protected Command createCommand(double amount) {
        return new DepositCommand(account, amount);
    }

    protected void displayResult(Command command, double amount) {
        JOptionPane.showMessageDialog(frame, amount + EURO + " added do you account!",
            "Lodgement", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(frame, "New balance = " + account.getBalance() + EURO,
            "Lodgement", JOptionPane.INFORMATION_MESSAGE);
    }
}
