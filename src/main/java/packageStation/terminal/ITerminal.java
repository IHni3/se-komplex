package packageStation.terminal;

import employee.IdCard;

public interface ITerminal {
    public void swipeIdCard(IdCard idCard);

    boolean pinConfirmed(IdCard idCard);

    boolean superPinConfirmed(IdCard idCard);

    public String getPinEncrypted(IdCard idCard);
}
