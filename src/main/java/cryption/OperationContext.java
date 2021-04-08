package cryption;


public class OperationContext {
    private final IStrategy strategy;

    public OperationContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String strToDecrypt, String secret) throws Exception {
        return strategy.doOperation(strToDecrypt, secret);
    }

}
