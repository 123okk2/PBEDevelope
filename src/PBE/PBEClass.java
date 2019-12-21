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
			//PBEŰ ����
			PBEKeySpec keySpec = new PBEKeySpec(password);
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			//���Ű ����
			SecretKey key = keyFac.generateSecret(keySpec);
			//Cipher ����
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			//��ȣ�� ����
			byte[] cipherText = cipher.doFinal(text.getBytes());
			//Base64 ���ڵ�
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
			//Base64 ���ڵ�
			byte[] saltB = Base64.decode(salt);
			byte[] cipherB = Base64.decode(cipherText);
			//PBEŰ ����
			PBEKeySpec keySpec = new PBEKeySpec(password);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			//���Ű ����
			SecretKey key = keyFactory.generateSecret(keySpec);
			//Cipher ����
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltB, 1000);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			//��ȣ�� ����
			byte[] plaintextArry = cipher.doFinal(cipherB);
			  
			text = new String(plaintextArry);
			
		}catch(Exception e) {
			e.printStackTrace();
			text = "Error Occure";
		}
		  
		  return text;
	}
	
}
