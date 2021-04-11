package encryption.des;


import encryption.IEncryptionAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DES implements IEncryptionAlgorithm {

    public String decrypt(String message, String key) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "DES"));
            byte[] decrypted = cipher.doFinal(message.getBytes());
            return new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    public String encrypt(String message, String key) throws Exception {

        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "DES"));
            byte[] encrypted = cipher.doFinal(message.getBytes());
            return new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}

