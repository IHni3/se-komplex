package cryption;


public class OperationAesEncrypt implements IStrategy {
    public String doOperation(String strToEncrypt, String secret) throws Exception {
        ICryptionAlgorithm aes = new AES();
        return aes.encrypt(strToEncrypt, secret);
    }
}
