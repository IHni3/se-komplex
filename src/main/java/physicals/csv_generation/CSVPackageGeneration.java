package physicals.csv_generation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import physicals.Package;
import main_configuration.Configuration;


public class CSVPackageGeneration {
    private List<String[]> packagesList = new ArrayList<>();

    public void start(Package[] packages) throws IOException {
        getPackages(packages);
        createPackageCSV();
    }

    public void getPackages(Package[] packages) {

        for (Package p : packages) {
            packagesList.add(new String[]
                    {p.getId(), p.getContent(), p.getZipCode(), p.getType(), p.getWeight()});
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
        File csvOutputFile = new File(Configuration.instance.pathToPackageCSV);
        try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
            packagesList.stream()
                    .map(this::convertToCSV)
                    .forEach(printWriter::println);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
