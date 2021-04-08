package cryption;

public interface ICryptionAlgorithm {
    String decrypt(String strToDecrypt, String secret) throws Exception;
    String encrypt(String strToDecrypt, String secret) throws Exception;
}
