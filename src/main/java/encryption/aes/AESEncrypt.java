package encryption.aes;


import encryption.IStrategy;

public class AESEncrypt implements IStrategy {
    public String doCipher(String message, String key) throws Exception {
        return new AES().encrypt(message, key);
    }
}
