package packageStation.terminal;

import employee.IDCard;

public interface ITerminal {
    void swipeIdCard(IDCard idCard);
    boolean pinConfirmed(IDCard idCard);
    boolean superPinConfirmed(IDCard idCard);
    String getPinEncrypted(IDCard idCard);
}
