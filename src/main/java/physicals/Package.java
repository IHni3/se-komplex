package physicals;

import main_configuration.Configuration;

import java.util.Random;

public class Package {

    private String id;
    private char[][][] content = new char[Configuration.instance.packageLength][Configuration.instance.packageWidth][Configuration.instance.packageHeight];
    private String zipCode;
    private Type type;
    private float weight;

    public void generate() {

        Random random = new Random();
        id = random.ints(48, 123)
                .filter(i -> (i <= 57 || i >= 97))
                .limit(Configuration.instance.lengthOfPackageID)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String pool = "abcdefghijklmnopqrstuvwxyz.:-!";
        for (int packageLength = 0; packageLength < content.length; packageLength++) {
            for (int packageWidth = 0; packageWidth < content[0].length; packageWidth++) {
                for (int packageHeight = 0; packageHeight < content[0][0].length; packageHeight++) {
                    content[packageLength][packageWidth][packageHeight] = pool.charAt(random.nextInt(pool.length()));
                }
            }
        }


        zipCode = generateValidZipCode();
        type = generateType();
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

    public void setContent(char[][][] content) {
        this.content = content;
    }

    private String generateValidZipCode() {
        Random random = new Random();
        int code = random.nextInt(99999);
        if (code <= 9999) {
            if (code < 1067) {
                code = code + 1067;
            }
            return "0" + code;
        }
        return String.valueOf(code);
    }

    private Type generateType() {
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

    public void setToExplosive() {

        String explosive = Configuration.instance.searchPattern;

        for (int i = 0; i < 9; i++) {
            content[6][3][i] = explosive.charAt(i);
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
        NORMAL,
        EXPRESS,
        VALUE
    }
}