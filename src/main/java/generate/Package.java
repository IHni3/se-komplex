package generate;

import java.util.Random;

public class Package {

    String id; // [a-z, 0-9] 6 digits
    char[][][] content = new char[25][10][10]; // [a-z|.|:|-|!]
    String zip; //[01067 - 99998]
    Type type;
    float weight;       // [1.00 - 5.00]

    public void generate() {

        // generate random ID
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;                 // 6 chars.
        Random random = new Random();
        id = random.ints(leftLimit, rightLimit + 1)
                // leave out Unicode chars and Capitalized Letters.
                .filter(i -> (i <= 57 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        // generate random content
        String pool = "abcdefghijklmnopqrstuvwxyz.:-!";
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    // picks one random char out of the pool
                    content[i][j][k] = pool.charAt(random.nextInt(pool.length()));
                }
            }
        }

        // generate random zip code
        int zipCode = random.nextInt(98831) + 1067;
        if (zipCode <= 9999) {
            zip = "0" + String.valueOf(zipCode);
        } else zip = String.valueOf(zipCode);


        // generate type according to possibility
        int chance = random.nextInt(100);
        if (chance <= 80) {
            type = Type.NORMAL;
        } else {
            if (chance <= 95) {
                type = Type.EXPRESS;
            } else {
                type = Type.VALUE;
            }
        }


        // generate weight
        weight = (random.nextFloat() * 4) + 1;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        String result = "";
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 10; j++) {
                char[] expat = new char[10];
                for (int k = 0; k < 10; k++) {
                    expat[k] = content[i][j][k];
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

    public void setToExplosive() {

        String explosive = "exp|os!ve";

        for (int i = 0; i < 9; i++) {
            content[7][3][i] = explosive.charAt(i);
        }
    }

    public void setID(String setID) {
        this.id = setID;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String setZip) {
        this.zip = setZip;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String setType) {
        switch (setType) {
            case "NORMAL" -> this.type = Type.NORMAL;
            case "EXPRESS" -> this.type = Type.EXPRESS;
            case "VALUE" -> this.type = Type.VALUE;
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
