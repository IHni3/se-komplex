package encryption.des;


import encryption.IStrategy;

public class DESEncrypt implements IStrategy {
    @Override
    public String doCipher(String message, String key) throws Exception {
        return new DES().encrypt(message, key);
    }
}

