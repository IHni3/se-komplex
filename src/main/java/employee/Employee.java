package employee;

import employee.enums.Profile;
import employee.enums.Roles;
import main_configuration.Configuration;

public class Employee {
    private int employeeID;
    private String name;
    private IDCard idCard;
    private Roles role;
    private String pin;
    private String superPin;
    private boolean isSenior;
    private Profile profile;

    public Employee(int employeeID, String name, Roles role, String pin, String superPin) throws Exception {
        setGeneralParameters(employeeID, name, role, pin, superPin);
        generateIDCard();
    }
    public Employee(int employeeID, String name, Roles role, String pin, String superPin, boolean isSenior) throws Exception
    {
        setGeneralParameters(employeeID, name, role, pin, superPin);
        generateIDCard();
        if(role==Roles.SUPERVISOR) {
            this.isSenior = isSenior;
        }
    }
    public Employee(int employeeID, String name, Roles role, String pin, String superPin, Profile profile) throws Exception{
        setGeneralParameters(employeeID, name, role, pin, superPin);
        generateIDCard();
        if(role==Roles.ADMINISTRATOR)
        {
            this.profile=profile;
        }
    }

    private void generateIDCard() throws Exception
    {
        idCard = new IDCard();
        idCard.encryptMagnetStripe(this.employeeID, this.name, this.role, this.pin, this.superPin);
    }

    private void setGeneralParameters(int id, String name, Roles role, String pin, String superPin) throws Exception
    {
        this.employeeID = id;
        this.name = name;
        this.role = role;
        if(pin.length()!= Configuration.instance.pinLength) {
            throw new Exception("wrong pin length");
        }
        this.pin = pin;
        if(superPin.length()!= Configuration.instance.superPinLength) {
            throw new Exception("wrong super pin length");
        }

        this.superPin = superPin;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IDCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IDCard idCard) {
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
}
