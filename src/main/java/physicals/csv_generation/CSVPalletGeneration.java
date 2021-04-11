package physicals.csv_generation;

import main_configuration.Configuration;
import physicals.Pallet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CSVPalletGeneration {


    private final List<String[]> palletList = new ArrayList<>();

    public void start(Pallet[] pallet) throws IOException {
        getBoxes(pallet);
        createPackageCSV();
    }

    public void getBoxes(Pallet[] pallet) {
        for (Pallet p : pallet) {
            for (int lvl = 0; lvl < Configuration.instance.numberOfPalletLevels; lvl++) {
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        palletList.add(new String[]
                                {String.valueOf(p.getPalletID()), String.valueOf(x + 2 * y), String.valueOf(lvl), p.getBoxID(x, y, lvl)});
                    }
                }
            }
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public void createPackageCSV() throws IOException {
        File csvOutputFile = new File(Configuration.instance.pathToPalletCSV);
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            palletList.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        }
    }


}