package com.cex.backend.utils;

/*
 * @author Ángel Albaladejo Flores
 */

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Utils {
	
	private static final SecureRandom secureRandom = new SecureRandom(); 
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	
	public static String encriptPasswword(String password) 
    {  
        MessageDigest md;
        
        StringBuilder hexString = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			 
	        BigInteger number = new BigInteger(1, md.digest(password.getBytes(StandardCharsets.UTF_8)));  
	  
	        hexString = new StringBuilder(number.toString(16));  
	  
	        while (hexString.length() < 32)  
	        {  
	            hexString.insert(0, '0');  
	        }  
		} catch (NoSuchAlgorithmException e) {
			hexString = null;
		}  
  
        return hexString.toString();
    }  

	public static String generateNewToken() {
	    byte[] randomBytes = new byte[255];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
}