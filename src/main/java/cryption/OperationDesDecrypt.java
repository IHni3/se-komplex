package cryption;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class OperationDesDecrypt implements IStrategy {
    @Override
    public String doOperation(String strToDecrypt, String secret) throws Exception {
        var des = new DES();
        return des.decrypt(strToDecrypt,secret);
    }
}
