package mainConfiguration;

public enum Configuration {
    instance;
    public int numberOfPackages = 24000;
    public int numberOfBoxes = 600;
    public int numberOfPallets = 50;
    public int numberOfLKWS = 5;

    public int packageHeight = 10;
    public int packageWidth = 10;
    public int packageLength = 25;

    public int numberOfBoxLevels = 5;
    public int numberOfPackagesInBox = 8;
    public int numberOfBoxLayers = 3;


    public int numberOfUnloadingZones = 7;
    public int numberOfParkingZoneAutonom = 5;

    public String nameOfAdministrator = "Armin Admin";


    public String superPin = "superPinKlasse";

    public String aesAlgorithm = "AES";
    public String desAlgorithm = "DES";

    public String secretKey = "dhbw";

    public String algorithmBM = "algortihms/boyer_moore";
    public String algorithmRK = "algortihms/rabin_karp";

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;
    public String pathToBoyerMooreJavaArchive = commonPathToJavaArchive + "boyerMoore" + fileSeparator + "jar" + fileSeparator + "boyerMoore.jar";
    public String pathToRabinKarpJavaArchive = commonPathToJavaArchive + "rabinKarp" + fileSeparator + "jar" + fileSeparator + "rabinKarp.jar";

}