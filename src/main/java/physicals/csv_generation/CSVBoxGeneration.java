package physicals.csv_generation;

import main_configuration.Configuration;
import physicals.Box;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CSVBoxGeneration {


    private final List<String[]> boxes = new ArrayList<>();

    public void start(Box[] boxes) throws IOException {
        getBoxes(boxes);
        creatBoxCSV();
    }

    public void getBoxes(Box[] boxes) {
        for (Box b : boxes) {
            this.boxes.add(new String[]
                    {b.getBoxID(), b.getPackageID()});
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

    public void creatBoxCSV() throws IOException {
        File csvOutputFile = new File(Configuration.instance.pathToBoxCSV);
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            boxes.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        }
    }


}
