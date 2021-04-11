package employee;

public class MagnetStripe {

    private String stripe;

    public String getStripe() {
        return stripe;
    }

    public void setStripe(String encryptedString) {
        stripe = encryptedString;
    }
}
