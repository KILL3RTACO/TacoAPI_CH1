package com.kill3rtaco.api.commands.test;

import com.kill3rtaco.api.commands.Command;

public class TestDevCommand extends Command {

	public TestDevCommand() {
		super("dev", null, "", "Dev shit");
	}

	@Override
	public void onCommand(String[] args) {
		System.out.println("I am a dev");
	}

}
