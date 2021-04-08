package packageStation.terminal;

import cryption.OperationAesDecrypt;
import cryption.OperationContext;
import cryption.OperationDesDecrypt;
import employee.Employee;
import employee.IdCard;
import employee.state.Active;
import employee.state.Invalid;
import employee.state.Locked;
import main_configuration.Configuration;
import packageStation.ControlUnit;
import packageStation.terminal.touchpad.Touchpad;

public class Terminal {
    private final String encryptionType = Configuration.instance.aesAlgorithm;

    private int wrongPinCounter = 0;
    private int wrongSuperPinCounter = 0;
    private boolean authorized;
    private final ControlUnit controlUnit;
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

    public boolean swipeIdCard(IdCard idCard, String input) throws Exception {
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
            System.out.println("This Id Card is rendered invalid. Please turn it in to local authorities or the front desk");
            return false;
        } else {
            System.out.println("invalid State");
            return false;
        }

    }

    public String[] decrypt(String magnetstripe) throws Exception {

        if (encryptionType.equals(Configuration.instance.aesAlgorithm)) {
            System.out.println(magnetstripe);

            OperationContext context = new OperationContext(new OperationAesDecrypt());
            magnetstripe = context.executeStrategy(magnetstripe, Configuration.instance.secretKey);
            System.out.println(magnetstripe);

        } else if (encryptionType.equals((Configuration.instance.desAlgorithm))) {
            System.out.println(magnetstripe);

            OperationContext context = new OperationContext(new OperationDesDecrypt());
            magnetstripe = context.executeStrategy(magnetstripe, Configuration.instance.secretKey);

            System.out.println(magnetstripe);
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
