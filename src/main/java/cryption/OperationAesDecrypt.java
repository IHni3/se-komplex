package cryption;


public class OperationAesDecrypt implements IStrategy {

    public String doOperation(String strToDecrypt, String secret) throws Exception {
        ICryptionAlgorithm aes = new AES();
        return aes.decrypt(strToDecrypt, secret);
    }
}
