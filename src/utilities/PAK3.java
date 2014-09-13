package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import main.ETConsole;

public class PAK3 {
	
	public static ArrayList<ZipEntry> load(String filePath, ETConsole console) throws IOException {
		if(filePath.contains(".") && !filePath.split("\\.")[1].equals("pk3")) {
			console.println("WARNING: " + filePath + " is an invalid pak3 file.");
			return null;
		}
		
		ArrayList<ZipEntry> fileList = new ArrayList<ZipEntry>();
		ZipInputStream stream = new ZipInputStream(new FileInputStream(filePath));
		ZipEntry entry = stream.getNextEntry();
				
		while(entry != null) {
			fileList.add(entry);
			entry = stream.getNextEntry();
		}
		
		stream.closeEntry();
		stream.close();
		
		return fileList;
	}
	
	
	
}
