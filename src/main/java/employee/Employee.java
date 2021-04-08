package employee;

public class Employee {
    private int id;
    private String name;
    private IdCard idCard;
    private Roles role;
    private String pin;
    private String superPin;

    public Employee(int id, String name, Roles role, String pin, String superPin) throws Exception {
        this.id = id;
        this.name = name;
        this.role = role;
        this.pin = pin;
        this.superPin = superPin;
        idCard = new IdCard();
        idCard.encryptMagnetStripe(this.id, this.name, this.role, this.pin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSuperPin() {
        return superPin;
    }

    public void setSuperPin(String superPin) {
        this.superPin = superPin;
    }

    public Employee getThis() {
        return this;
    }
}
