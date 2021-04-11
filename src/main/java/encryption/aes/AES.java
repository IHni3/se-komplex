package encryption.aes;

import encryption.IEncryptionAlgorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES implements IEncryptionAlgorithm {
    private SecretKeySpec secretKey;

    private void setKey(String enteredKey) {

        try {
            byte[] key = enteredKey.getBytes(StandardCharsets.UTF_8);
            key = MessageDigest.getInstance("SHA-1").digest(key);
            secretKey = new SecretKeySpec(Arrays.copyOf(key, 16), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String message, String key) throws Exception{
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }


    public String decrypt(String message, String key) throws Exception{
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}

