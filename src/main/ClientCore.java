package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;

import javax.swing.ImageIcon;
import utilities.PAK3;

public class ClientCore {
	private static ImageIcon icon = new ImageIcon("icon.png");
	private static Map<String, ArrayList<ZipEntry>> pakList = new HashMap<String, ArrayList<ZipEntry>>();
	
	public static void main(String[] args) {
		ETConsole console = new ETConsole();
		
		console.println("Booting Java-ET...");
		
		try {
			Files.walk(Paths.get("jetmain/")).forEach(filePath -> {
				if (!Files.isRegularFile(filePath))
					return;
				
				ArrayList<ZipEntry> entryList = new ArrayList<ZipEntry>();
				try {
					PAK3.load(pakList, entryList, filePath.toString(), console);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static ImageIcon getIcon() {
		return icon;
	}
}
