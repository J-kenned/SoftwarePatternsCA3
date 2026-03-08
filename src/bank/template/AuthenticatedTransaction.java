package bank.template;

import bank.model.CustomerAccount;
import bank.model.CustomerCurrentAccount;
import bank.command.Command;
import bank.command.CommandInvoker;
import bank.util.BankUtils;
import javax.swing.*;

public abstract class AuthenticatedTransaction {
    protected final JFrame frame;
    protected final CustomerAccount account;
    protected final CommandInvoker invoker;
    static final String EURO = "€";


    public AuthenticatedTransaction(JFrame frame, CustomerAccount account,
                                    CommandInvoker invoker) {
        this.frame = frame;
        this.account = account;
        this.invoker = invoker;
    }

    public final void execute() {
        if(!authenticate()) return;

        double amount = requestAmount();
        if(amount < 0) return;

        Command command = createCommand(amount);
        invoker.executeCommand(command);

        displayResult(command, amount);
    }

    private boolean authenticate() {
        if(!(account instanceof CustomerCurrentAccount currentAccount)) return true;
        int count = 3;
        int checkPin = currentAccount.getAtm().getPin();

        while(true) {
            if(count == 0) {
                JOptionPane.showMessageDialog(frame,
                    "Pin entered incorrectly 3 times. ATM card locked.",
                    "Pin", JOptionPane.INFORMATION_MESSAGE);
                currentAccount.getAtm().setValid(false);
                onLockout();
                return false;
            }
            String pin = JOptionPane.showInputDialog(frame, "Enter 4 digit PIN;");
            if(pin == null) return false;
            if(!BankUtils.isNumeric(pin)) {
                count--;
                JOptionPane.showMessageDialog(frame,
                    "Invalid PIN. " + count + " attempts remaining.",
                    "Pin", JOptionPane.INFORMATION_MESSAGE);
                continue;
            }
            int entered = Integer.parseInt(pin);
            if(checkPin == entered) {
                JOptionPane.showMessageDialog(frame, "Pin entry successful",
                    "Pin", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
            count--;
            JOptionPane.showMessageDialog(frame,
                "Incorrect pin. " + count + " attempts remaining.",
                "Pin", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected abstract void onLockout();
    protected abstract double requestAmount();
    protected abstract Command createCommand(double amount);
    protected abstract void displayResult(Command command, double amount);

}
