package encryption;

public interface IEncryptionAlgorithm {
    String decrypt(String message, String key) throws Exception;

    String encrypt(String message, String key) throws Exception;
}
