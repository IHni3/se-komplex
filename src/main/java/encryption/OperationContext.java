package encryption;


public class OperationContext {
    private final IStrategy strategy;

    public OperationContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public String executeStrategy(String message, String key) throws Exception {
        return strategy.doCipher(message, key);
    }

}
