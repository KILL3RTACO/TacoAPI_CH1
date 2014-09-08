package com.kill3rtaco.api.commands.test;

import com.kill3rtaco.api.commands.Controller;

public class TestExample extends Controller {

	public static void main(String[] args) {
		TestExample test = new TestExample();
		test.dispatchCommand("test dev");
		test.dispatchCommand("test user");
	}

	@Override
	public void registerCommands() {
		registerCommand(new TestCommandHandler());
	}

}
