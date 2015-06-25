package br.heusser.controleestoque.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author diego
 */
public class SHA2 {

    public static String sha2(String original) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(original.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String sha2 = hexString.toString();
        return sha2;
    }
}
