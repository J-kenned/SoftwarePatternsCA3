package bank.template;

import bank.model.CustomerAccount;
import bank.command.Command;
import bank.command.CommandInvoker;
import bank.command.customer.WithdrawCommand;
import bank.util.BankUtils;
import javax.swing.*;

public class WithdrawalTransaction extends AuthenticatedTransaction {
    private final Runnable onLockoutCallback;

    public WithdrawalTransaction(JFrame frame, CustomerAccount account,
                                 CommandInvoker invoker, Runnable onLockoutCallback) {
        super(frame, account, invoker);
        this.onLockoutCallback = onLockoutCallback;
    }

    protected void onLockout() {
        onLockoutCallback.run();
    }

    protected double requestAmount() {
        String balanceTest = JOptionPane.showInputDialog(frame, "Enter amount you wish to withdraw (max 500):");
        if(BankUtils.isNumeric(balanceTest)) {
            return Double.parseDouble(balanceTest);
        } else {
            JOptionPane.showMessageDialog(frame, "You must enter a numerical value!",
                "Oops!", JOptionPane.INFORMATION_MESSAGE);
            return -1;
        }
    }

    protected Command createCommand(double amount) {
        return new WithdrawCommand(account, amount);
    }

    protected void displayResult(Command command, double amount) {
        WithdrawCommand withdrawCmd = (WithdrawCommand) command;
        if(withdrawCmd.getResultMessage() != null) {
            JOptionPane.showMessageDialog(frame, withdrawCmd.getResultMessage(),
                "Oops!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, amount + EURO + " withdrawn.",
                "Withdraw", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(frame, "New balance = " + account.getBalance() + EURO,
                "Withdraw", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
