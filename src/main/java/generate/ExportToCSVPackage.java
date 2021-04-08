package generate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExportToCSVPackage {


    List<String[]> packages = new ArrayList<>();

    public void start(Package[] packs) throws IOException {
        getPackages(packs);
        givenDataArray_whenConvertToCSV_thenOutputCreated();
    }

    public void getPackages(Package[] packs) {

        for (Package p : packs) {
            packages.add(new String[]
                    {p.getId(), p.getContent(), p.getZip(), p.getType(), p.getWeight()});
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
        File csvOutputFile = new File("src/main/java/CSV Daten/base_package.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            packages.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
