package packageStation.terminal;

import employee.IdCard;

public interface ITerminal {
    void swipeIdCard(IdCard idCard);
    boolean pinConfirmed(IdCard idCard);
    boolean superPinConfirmed(IdCard idCard);
    String getPinEncrypted(IdCard idCard);
}
