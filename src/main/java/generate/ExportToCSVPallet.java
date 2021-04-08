package generate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExportToCSVPallet {


    private List<String[]> pallets = new ArrayList<>();

    public void start(Pallet[] pallet) throws IOException {
        getBoxes(pallet);
        givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public void getBoxes(Pallet[] pallet) {
        for (Pallet p : pallet) {
            for (int lvl = 0; lvl < 3; lvl++) {
                for (int posX = 0; posX < 2; posX++) {
                    for (int posY = 0; posY < 2; posY++) {
                        pallets.add(new String[]
                                {String.valueOf(p.getID()), String.valueOf(posX + posY), String.valueOf(lvl), p.getBoxID(posX, posY, lvl)});
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

    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException { //TODO
        File csvOutputFile = new File("src/main/java/CSV Daten/base_pallet.csv"); //TODO
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pallets.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }


}