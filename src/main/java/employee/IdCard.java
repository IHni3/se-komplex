package employee;

import cryption.OperationAesEncrypt;
import cryption.OperationContext;
import cryption.OperationDesEncrypt;
import employee.state.Active;
import employee.state.IIdCardState;
import main_configuration.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdCard {
    private final MagnetStripe magnetStripe;
    private IIdCardState idCardState;
    private final List<String> information = new ArrayList<>();
    private final String encryptionType = Configuration.instance.aesAlgorithm;

    public IdCard() {
        setIDCardState(new Active());
        magnetStripe = new MagnetStripe();
    }

    public MagnetStripe getMagnetStripe() {
        return magnetStripe;
    }

    public void setIDCardState(IIdCardState idCardState) {
        this.idCardState = idCardState;
    }

    public IIdCardState getState() {
        return idCardState;
    }

    public void encryptMagnetStripe(int id, String name, Roles role, String pin) throws Exception {
        information.addAll(Arrays.asList(String.valueOf(id),
                name,
                role.toString(),
                Configuration.instance.superPin));

        String combined = "";
        for (var info : information) {
            combined += info + ";";
        }

        System.out.println("raw: " + combined);

        if (encryptionType.equals(Configuration.instance.aesAlgorithm)) {
            OperationContext context = new OperationContext(new OperationAesEncrypt());
            combined = context.executeStrategy(combined, Configuration.instance.secretKey);
            System.out.println("aes encrypted: " + combined);
        } else if (encryptionType.equals((Configuration.instance.desAlgorithm))) {
            OperationContext context = new OperationContext(new OperationDesEncrypt());
            combined = context.executeStrategy(combined, Configuration.instance.secretKey);
            System.out.println("des encrypted: " + combined);
        } else {

        }
        magnetStripe.write(combined);
    }
}
