package packageStation.terminal;

import employee.Employee;
import employee.IDCard;
import employee.state.Active;
import employee.state.Invalid;
import employee.state.Locked;
import encryption.OperationContext;
import encryption.aes.AESDecrypt;
import encryption.des.DESDecrypt;
import main_configuration.Configuration;
import packageStation.ControlUnit;
import packageStation.terminal.touchpad.Touchpad;

public class Terminal{
    private final String encryptionType = Configuration.instance.aesAlgorithm;
    private final ControlUnit controlUnit;
    private int wrongPinCounter = 0;
    private int wrongSuperPinCounter = 0;
    private boolean authorized;
    private Employee employee;

    public Terminal(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;

    }

    public void registerEmployee(Employee employee, String pin) throws Exception {
        if (swipeIdCard(employee.getIdCard(), pin)) {
            this.employee = employee;
            enableTouchpad();
        }
    }

    private void enableTouchpad() {
        if (employee != null) {
            Touchpad touchpad = new Touchpad(employee, controlUnit);

        }
    }

    public boolean swipeIdCard(IDCard idCard, String input) throws Exception {
        String[] information = decrypt(idCard.getMagnetStripe().getStripe());
        if (idCard.getState().equals(new Active())) {
            if (information[3].equals(input)) {
                return true;
            } else {
                wrongPinCounter++;
                if (wrongPinCounter >= 3) {
                    idCard.setIDCardState(new Locked());
                }
                return false;
            }
        } else if (idCard.getState().equals(new Locked())) {
            if (information[4].equals(input)) {
                idCard.setIDCardState(new Active());
                return true;
            } else {
                wrongSuperPinCounter++;
                if (wrongSuperPinCounter >= 2) {
                    idCard.setIDCardState(new Invalid());
                }
                return false;
            }
        } else if (idCard.getState().equals(new Invalid())) {
            return false;
        } else {
            return false;
        }

    }

    public String[] decrypt(String magnetstripe) throws Exception {

        if (encryptionType.equals(Configuration.instance.aesAlgorithm)) {


            OperationContext context = new OperationContext(new AESDecrypt());
            magnetstripe = context.executeStrategy(magnetstripe, Configuration.instance.secretKey);


        } else if (encryptionType.equals((Configuration.instance.desAlgorithm))) {


            OperationContext context = new OperationContext(new DESDecrypt());
            magnetstripe = context.executeStrategy(magnetstripe, Configuration.instance.secretKey);


        }
        String[] information = magnetstripe.split(";");

        int id = Integer.parseInt(information[0]);
        String name = information[1];
        String role = information[2];
        String pin = information[3];
        String superPin = information[4];
        String combined = id + ";" + name + ";" + role + ";" + pin + ";" + superPin;
        System.out.println(combined);


        return information;
    }


}
