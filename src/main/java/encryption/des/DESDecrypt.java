package encryption.des;


import encryption.IStrategy;

public class DESDecrypt implements IStrategy {
    @Override
    public String doCipher(String message, String key) throws Exception {
        return new DES().decrypt(message, key);
    }
}
