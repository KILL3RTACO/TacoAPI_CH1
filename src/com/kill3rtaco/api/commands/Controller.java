package com.kill3rtaco.api.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class Controller {

	private List<CommandHandler> _commands;

	public Controller() {
		_commands = new ArrayList<CommandHandler>();
		registerCommands();
	}

	public void registerCommand(CommandHandler command) {
		_commands.add(command);
	}

	public void dispatchCommand(String command) {
		if (command == null || command.isEmpty())
			throw new IllegalArgumentException("The coommand cannot be null or empty");

		String[] args = command.split("\\s+");
		String cmd = args[0];
		args = StringUtils.removeFirstArg(args);
		for (CommandHandler c : _commands) {
			if (c.hasAlias(cmd)) {
				c.onCommand(cmd, args);
				return;
			}
		}
		System.out.println("Unknown command \"" + cmd + "\"");
	}

	public abstract void registerCommands();

}
