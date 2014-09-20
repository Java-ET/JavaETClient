package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryption {
	private static String passwordToEncrypt;
	
	private static final String HASH_SECRET = "30488vxxjDDas";
	
	public static String encryptPassword(String password) throws NoSuchAlgorithmException
	{
		passwordToEncrypt = password;
		
		int length = passwordToEncrypt.length();
		
		StringBuilder sB = new StringBuilder();
		
		
		for(int i = 0; i < length; i++)
		{
			sB.append(passwordToEncrypt.charAt(i));
			if(i%2 == 0) sB.append(HASH_SECRET);
		}
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		md.update(sB.toString().getBytes());
		
		byte byteData[] = md.digest();
		
		StringBuffer hexString = new StringBuffer();
		
		for(int j = 0; j <byteData.length; j++)
		{
			String hex = Integer.toHexString(0xff & byteData[j]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}
		
		return hexString.toString();
		
	}
}
