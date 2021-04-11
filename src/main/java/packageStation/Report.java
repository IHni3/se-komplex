package packageStation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Report {
    private final Date currentDate;
    private final int dispatchedTrucksCount;
    private final int[] countPackagesGroupedByType;
    private final int dangerousPackagesCount;

    private Report(Builder builder) {
        currentDate = builder.currentDate;
        dispatchedTrucksCount = builder.dispatchedTrucksCount;
        countPackagesGroupedByType = builder.countPackagesGroupedByType;
        dangerousPackagesCount = builder.dangerousPackagesCount;
        output();

    }

    public void output() {

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter("report.txt")));
            printWriter.println("date: " + currentDate);
            printWriter.println("dispatched Trucks: " + dispatchedTrucksCount);
            printWriter.println("Normal Packages: " + countPackagesGroupedByType[0]);
            printWriter.println("Express Packages: " + countPackagesGroupedByType[1]);
            printWriter.println("Value Packages: " + countPackagesGroupedByType[2]);
            printWriter.println("Dangerous Packages: " + dangerousPackagesCount);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public static class Builder {
        private Date currentDate;
        private int dispatchedTrucksCount;
        private final int[] countPackagesGroupedByType = new int[3];
        private int dangerousPackagesCount;

        public Builder date() {
            this.currentDate = new Date();
            return this;
        }

        public Builder dispatchedTrucksCount(int amount) {
            this.dispatchedTrucksCount = amount;
            return this;
        }


        public Builder countPackagesGroupedByType(int[] setNumberOfPackagesGroupedByType) {
            this.countPackagesGroupedByType[0] = setNumberOfPackagesGroupedByType[0]; //Anzahl der Pakete mit NORMAL
            this.countPackagesGroupedByType[1] = setNumberOfPackagesGroupedByType[1]; //Anzahl der Pakete mit EXPRESS
            this.countPackagesGroupedByType[2] = setNumberOfPackagesGroupedByType[2]; // Anzahl der Pakete mit VALUE
            return this;
        }

        public Builder dangerousPackagesCount(int amount) {
            dangerousPackagesCount = amount;
            return this;
        }


        public Report build() {
            return new Report(this);
        }
    }
}
