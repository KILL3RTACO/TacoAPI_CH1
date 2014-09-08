package com.kill3rtaco.api.commands.test;

import com.kill3rtaco.api.commands.Command;

public class TestUserCommand extends Command {

	public TestUserCommand() {
		super("user", null, "", "User shit");
	}

	@Override
	public void onCommand(String[] args) {
		System.out.println("I am a user");
	}

}
