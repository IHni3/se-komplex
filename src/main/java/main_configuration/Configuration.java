package main_configuration;

public enum Configuration {
    instance;

    public int numberOfPackages = 24000;
    public int packageHeight = 10;
    public int packageWidth = 10;
    public int packageLength = 25;
    public int lengthOfPackageID = 6;


    public int numberOfBoxes = 600;
    public int numberOfBoxLevels = 5;
    public int numberOfPackagesInBox = 40;
    public int lengthOfBoxID = 5;


    public int numberOfPallets = 50;
    public int numberOfPalletLevels = 3;

    public int numberOfTrucks = 5;
    public int lengthOfTruckID = 4;
    public int numberOfPalletsOnTrailer = 10;


    public int storageTrackCapacity = 600;

    public int numberOfStorageTracks = 8;

    public int numberOfSortingTracks = 3;

    public int numberOfTemporaryStoragePositions = 5;
    public int numberOfTemporaryStorageLayers = 2;



    public int numberOfUnloadingZones = 7;
    public int numberOfAutonomousCars = 5;

    public int pinLength = 4;
    public int superPinLength = 6;

    public String searchPattern = "exp!os:ve";


    public String aesAlgorithm = "AES";
    public String desAlgorithm = "DES";

    public String secretKey = "dhbw";

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;
    public String pathToBoyerMooreJavaArchive = commonPathToJavaArchive + "boyerMoore" + fileSeparator + "jar" + fileSeparator + "boyerMoore.jar";
    public String pathToRabinKarpJavaArchive = commonPathToJavaArchive + "rabinKarp" + fileSeparator + "jar" + fileSeparator + "rabinKarp.jar";
    public String pathToBoxCSV = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "csv_files" + fileSeparator + "base_box.csv";
    public String pathToPackageCSV = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "csv_files" + fileSeparator + "base_package.csv";
    public String pathToPalletCSV = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "csv_files" + fileSeparator + "base_pallet.csv";
    public String pathToTruckCSV = userDirectory + fileSeparator + "src" + fileSeparator + "main" + fileSeparator + "java" + fileSeparator + "csv_files" + fileSeparator + "base_truck.csv";

}
