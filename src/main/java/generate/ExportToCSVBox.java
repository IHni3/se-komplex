package generate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExportToCSVBox {


    private final List<String[]> boxes = new ArrayList<>();

    public void start(Box[] boxes) throws IOException {
        getBoxes(boxes);
        givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public void getBoxes(Box[] boxes) {
        for (Box b : boxes) {
            this.boxes.add(new String[]
                    {b.getId(), b.getPackageID()});
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
        File csvOutputFile = new File("src/main/java/csv_files/base_box.csv"); //TODO into config
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            boxes.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }


}
