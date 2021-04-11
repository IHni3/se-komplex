package encryption;

public interface IStrategy {
    String doCipher(String message, String key) throws Exception;
}
