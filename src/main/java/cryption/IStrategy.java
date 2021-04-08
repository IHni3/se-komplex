package cryption;

public interface IStrategy {
    String doOperation(String strToDecrypt, String secret) throws Exception;
}
