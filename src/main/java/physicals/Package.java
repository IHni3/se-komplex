package physicals;

import java.util.Random;
import main_configuration.Configuration;

public class Package {

    private String id; // [a-z, 0-9] 6 digits
    private char[][][] content = new char[Configuration.instance.packageLength][Configuration.instance.packageWidth][Configuration.instance.packageHeight]; // [a-z|.|:|-|!]
    private String zipCode; //[01067 - 99998]
    private Type type;
    private float weight;       // [1.00 - 5.00]

    public void generate() {

        // generate random ID

        Random random = new Random();
        id = random.ints(48, 123)
                // leave out Unicode chars and Capitalized Letters.
                .filter(i -> (i <= 57 || i >= 97))
                .limit(Configuration.instance.lengthOfPackageID)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // generate random content
        String pool = "abcdefghijklmnopqrstuvwxyz.:-!";
        for (int packageLength = 0; packageLength < content.length; packageLength++) {
            for (int packageWidth = 0; packageWidth < content[0].length; packageWidth++) {
                for (int packageHeight = 0; packageHeight < content[0][0].length; packageHeight++) {
                    // picks one random char out of the pool
                    content[packageLength][packageWidth][packageHeight] = pool.charAt(random.nextInt(pool.length()));
                }
            }
        }


        zipCode= generateValidZipCode();
        type=generateType();


        // generate weight
        weight = random.nextFloat() + random.nextInt(4) + 1;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        String result = "";
        for (int packageLength = 0; packageLength < content.length; packageLength++) {
            for (int packageWidth = 0; packageWidth < content[0].length; packageWidth++) {
                char[] expat = new char[10];
                for (int packageHeight = 0; packageHeight < content[0][0].length; packageHeight++) {
                    expat[packageHeight] = content[packageLength][packageWidth][packageHeight];
                }
                String string = new String(expat);
                result = result + string;
            }
        }
        return result;
    }

    private String generateValidZipCode()
    {
        Random random = new Random();
        int code = random.nextInt(99999);
        if(code <= 9999)
        {
            if(code<1067)
            {
                code=code+1067;
            }
            return "0" + String.valueOf(code);
        }
        return String.valueOf(code);
    }

    private Type generateType()
    {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 80) {
            return Type.NORMAL;
        } else {
            if (chance < 95) {
                return Type.EXPRESS;
            } else {
                return Type.VALUE;
            }
        }
    }

    public void setContent(char[][][] content) {
        this.content = content;
    }

    public void setToExplosive() {

        String explosive = "exp!os:ve";

        for (int i = 0; i < 9; i++) {
            content[i][3][3] = explosive.charAt(i);
        }
    }

    public void setID(String setID) {
        this.id = setID;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String setZip) {
        this.zipCode = setZip;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String setType) {
        switch (setType) {
            case "NORMAL" -> this.type = Type.NORMAL;
            case "EXPRESS" -> this.type = Type.EXPRESS;
            case "VALUE" -> this.type = Type.VALUE;
            default -> System.out.println("Wrong Type");
        }
    }

    public String getWeight() {
        return String.valueOf(weight);
    }

    public void setWeight(float setWeight) {
        this.weight = setWeight;
    }

    public Package getPackage() {
        return this;
    }

    enum Type {
        NORMAL,         // 80%
        EXPRESS,        // 15%
        VALUE           // 5%
    }
}