package employee;

import employee.enums.Roles;
import employee.state.Active;
import employee.state.IIdCardState;
import encryption.OperationContext;
import encryption.aes.AESEncrypt;
import encryption.des.DESEncrypt;
import main_configuration.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IDCard {
    private final MagnetStripe magnetStripe = new MagnetStripe();
    private final List<String> information = new ArrayList<>();
    private final String encryptionType = Configuration.instance.aesAlgorithm;
    private IIdCardState state = new Active();

    public IDCard() {
    }

    public MagnetStripe getMagnetStripe() {
        return magnetStripe;
    }

    public void setIDCardState(IIdCardState state) {
        this.state = state;
    }

    public IIdCardState getState() {
        return state;
    }

    public void encryptMagnetStripe(int id, String name, Roles role, String pin, String superPin) throws Exception {
        information.addAll(Arrays.asList(String.valueOf(id),
                name,
                role.toString(),
                pin,
                superPin));

        String stripeInformation = "";
        for (var info : information) {
            stripeInformation += info + ";";
        }


        if (encryptionType.equals(Configuration.instance.aesAlgorithm)) {
            OperationContext context = new OperationContext(new AESEncrypt());
            stripeInformation = context.executeStrategy(stripeInformation, Configuration.instance.secretKey);

        } else if (encryptionType.equals((Configuration.instance.desAlgorithm))) {
            OperationContext context = new OperationContext(new DESEncrypt());
            stripeInformation = context.executeStrategy(stripeInformation, Configuration.instance.secretKey);

        } else {

        }
        magnetStripe.setStripe(stripeInformation);
    }
}
