package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import main.ETConsole;

public class PAK3 {
	
	public static void load(Map<String, ArrayList<ZipEntry>> pakList, ArrayList<ZipEntry> entryList, String filePath, ETConsole console) throws IOException {
		if(filePath.contains(".") && !filePath.split("\\.")[1].equals("pk3"))
			return;
		
		ZipInputStream stream = new ZipInputStream(new FileInputStream(filePath));
		ZipEntry entry = stream.getNextEntry();
		
		Date date = new Date();
		
		console.print(DateExtender.leadingZeros(date.getHours()) + ":" + DateExtender.leadingZeros(date.getMinutes()) + ":" + DateExtender.leadingZeros(date.getSeconds()) + " - Loading " + filePath + "...");
		
		while(entry != null) {
			entryList.add(entry);
			entry = stream.getNextEntry();
		}
		
		stream.closeEntry();
		stream.close();
		
		console.println(" complete.");
		pakList.put(filePath, entryList);
	}
	
	
	
}
