
package br.com.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Diego Alves
 */

public class CreateHash {
    
    public static String hashMDK5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, messageDigest.digest(senha.getBytes("UTF-8")));
        String pass = hash.toString(16);
        
        return pass;
    }

}
