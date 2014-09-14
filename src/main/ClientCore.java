package main;

import utilities.PAK3;

public class ClientCore {
	public static void main(String[] args) {
		ETConsole console = new ETConsole();
		console.println("Booting Java-ET Client...");
		PAK3.loadFiles(console);
		
		console.println("Game files loaded succesfully.");
		// Start the actual game here
	}
}
