package com.kill3rtaco.api.commands.test;

import com.kill3rtaco.api.commands.CommandHandler;

public class TestCommandHandler extends CommandHandler {

	public TestCommandHandler() {
		super("test", null, "Test shit");
	}

	@Override
	protected void registerCommands() {
		registerCommand(new TestDevCommand());
		registerCommand(new TestUserCommand());
	}

	@Override
	protected void onCommand() {
		System.out.println("THIS IS A TEST");
	}

}
