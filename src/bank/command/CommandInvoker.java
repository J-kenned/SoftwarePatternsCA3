package bank.command;

import java.util.ArrayList;

public class CommandInvoker {
	private final ArrayList<Command> history = new ArrayList<Command>();

	public void executeCommand(Command command) {
		command.execute();
		history.add(command);
	}

	public ArrayList<Command> getHistory() {
		return history;
	}
}
