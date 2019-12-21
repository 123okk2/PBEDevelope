package PBE;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class PBEClass {
	private char[] password;
	private String text;
	
	PBEClass(char[] password, String text) {
		this.password = password;
		this.text = text;
	}
	
	public String EnCrypt() {
		byte[] salt = new byte[8];
		Random r = new Random();
		r.nextBytes(salt);
		try {
			//PBE虐 积己
			PBEKeySpec keySpec = new PBEKeySpec(password);
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			//厚剐虐 积己
			SecretKey key = keyFac.generateSecret(keySpec);
			//Cipher 积己
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			//鞠龋巩 积己
			byte[] cipherText = cipher.doFinal(text.getBytes());
			//Base64 牢内爹
			String saltString = Base64.encode(salt);
			String cipherString = Base64.encode(cipherText);
			
			text = saltString + cipherString;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			text = "Error Occure";
		}
		
		return text;
		
	}
	
	public String DeCript() {
		String salt = text.substring(0,12);
		String cipherText = text.substring(12);
		try {
			//Base64 叼内爹
			byte[] saltB = Base64.decode(salt);
			byte[] cipherB = Base64.decode(cipherText);
			//PBE虐 积己
			PBEKeySpec keySpec = new PBEKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			//厚剐虐 积己
			SecretKey key = keyFactory.generateSecret(keySpec);
			//Cipher 积己
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltB, 1000);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			//汗龋巩 积己
			byte[] plaintextArry = cipher.doFinal(cipherB);
			  
			text = new String(plaintextArry);
			
		}catch(Exception e) {
			e.printStackTrace();
			text = "Error Occure";
		}
		  
		  return text;
	}
	
}
