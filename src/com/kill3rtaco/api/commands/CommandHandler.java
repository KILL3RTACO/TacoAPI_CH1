package com.kill3rtaco.api.commands;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a command. The name of this class should use the pattern
 * [CommandName]CommandHandler. e.g. if the command in the plugin.yml was 'give'
 * then the class name should be 'GiveCommandHandler'
 * 
 * @author KILL3RTACO
 * @see Command
 * @see TacoHelpCommand
 *
 */
public abstract class CommandHandler {

	private String cmdName, description;
	private String[] aliases;
	private ArrayList<Command> commands;

	public CommandHandler(String cmdName, String[] aliases, String description) {
		this.commands = new ArrayList<Command>();
		this.cmdName = cmdName;
		this.aliases = aliases;
		this.description = description;
		registerCommands();
	}

	/**
	 * Method used to register subcommands
	 */
	protected abstract void registerCommands();

	public boolean hasAlias(String alias) {
		if (alias.equalsIgnoreCase(cmdName))
			return true;
		if (aliases == null || aliases.length == 0)
			return false;
		for (String s : aliases) {
			if (alias.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	protected void showHelp(int page) {
		Collections.sort(commands);
		PageBuilder help = new PageBuilder(StringUtils.toProperCase(cmdName) + " Help", "");
		help.append(cmdName + ": " + description);
		help.append(cmdName + " <help/?> [page]: Shows help");
		for (Command tc : commands) {
			help.append(cmdName + " " + tc.getName() + " " + tc.getArgs() + ": " + tc.getDescription());
		}
		help.showPage(page);
	}

	/**
	 * Registers a subcommand for this command. A subcommand is the first
	 * argument in a command
	 * 
	 * @param command
	 *            - The TacoCommand to register
	 */
	protected void registerCommand(Command command) {
		commands.add(command);
	}

	/**
	 * Default Bukkit onCommand() call. This is used to filter the command being
	 * used, and will run if found.
	 */
	public void onCommand(String label, String[] args) {
		if (args.length > 0 && (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?"))) {
			if (args.length == 1) { // "help"
				showHelp(1);
			} else { // "help [page]"
				try {
					int page = Integer.parseInt(args[1]);
					showHelp(page);
				} catch (NumberFormatException e) {
					showHelp(1);
				}
			}
		} else {
			runCommand(args);
		}
	}

	private void runCommand(String[] args) {
		String subcommand = args[0];
		Command tc = getCommandByAlias(subcommand);
		args = StringUtils.removeFirstArg(args);
		if (tc != null) {
			tc.onCommand(args);
		} else {
			System.out.println("Invalid subcommand");
		}
	}

	/**
	 * Gets the name of this command.
	 * 
	 * @return The name of this {@link CommandHandler TacoCommandHandler}
	 */
	public String getName() {
		return cmdName;
	}

	/**
	 * Called when this command is run with no arguments by the console.
	 * 
	 * @return false if this command must be run by a player
	 */
	protected abstract void onCommand();

	/**
	 * Get a command by giving an alias
	 * 
	 * @param alias
	 *            the alias used to get the TacoCommand
	 * @return the TacoCommand found, or null if there wasn't one
	 */
	public Command getCommandByAlias(String alias) {
		for (Command tc : commands)
			if (tc.hasAlias(alias))
				return tc;
		return null;
	}
}
