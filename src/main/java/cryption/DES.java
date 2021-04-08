package cryption;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DES implements ICryptionAlgorithm {

    public String decrypt(String strToDecrypt, String secret) throws Exception {
        try {
            SecretKeySpec skeyspec = new SecretKeySpec(secret.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted = cipher.doFinal(strToDecrypt.getBytes());
            return new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    public String encrypt(String strToDecrypt, String secret) throws Exception {

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(secret.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted = cipher.doFinal(strToDecrypt.getBytes());
            return new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}

