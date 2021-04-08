package generate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExportToCSVTruck {


    List<String[]> trucks = new ArrayList<>();

    public void start(Truck[] trucks) throws IOException {
        getTrucks(trucks);
        givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public void getTrucks(Truck[] truck) {
        for (Truck t : truck) {
            for (int i = 0; i < 5; i++) {
                trucks.add(new String[]
                        {t.getTruckID(), "left", String.valueOf(i + 1), String.valueOf(t.getTrailer().getLeftPalletID(i))});
            }
            for (int i = 0; i < 5; i++) {
                trucks.add(new String[]
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

    public void givenDataArray_whenConvertToCSV_thenOutputCreated() throws IOException {
        File csvOutputFile = new File("src/main/java/CSV Daten/base_truck.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            trucks.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }


}
