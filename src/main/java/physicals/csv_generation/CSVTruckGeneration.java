package physicals.csv_generation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import physicals.Truck;
import main_configuration.Configuration;


public class CSVTruckGeneration {


    private List<String[]> listTrucks = new ArrayList<>();

    public void start(Truck[] trucks) throws IOException {
        getTrucks(trucks);
        createTruckCSV();
    }

    public void getTrucks(Truck[] truck) {
        for (Truck t : truck) {
            for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
                listTrucks.add(new String[]
                        {t.getTruckID(), "left", String.valueOf(i + 1), String.valueOf(t.getTrailer().getLeftPalletID(i))});
            }
            for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
                listTrucks.add(new String[]
                        {t.getTruckID(), "right", String.valueOf(i + 1), String.valueOf(t.getTrailer().getRightPalletID(i))});
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

    public void createTruckCSV() throws IOException {
        File csvOutputFile = new File(Configuration.instance.pathToTruckCSV);
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            listTrucks.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        }
    }


}
