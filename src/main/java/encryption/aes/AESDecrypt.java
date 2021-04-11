package encryption.aes;


import encryption.IStrategy;

public class AESDecrypt implements IStrategy {

    public String doCipher(String message, String key) throws Exception {
        return new AES().decrypt(message, key);
    }
}
