package packageStation.buildReport;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Report {
    private Date currentDate;
    private int numberOfDispatchedTrucks;
    private int[] numberOfPackagesGroupedByType;
    private int numberOfDangerousPackages;

    private Report(Builder builder) {
        currentDate = builder.currentDate;
        numberOfDispatchedTrucks = builder.numberOfDispatchedLKW;
        numberOfPackagesGroupedByType = builder.numberOfPackagesGroupedByType;
        numberOfDangerousPackages = builder.numberOfDangerousPackages;
        output();

    }

    public void output() {
        System.out.println("date: " + currentDate);
        System.out.println("number of dispatched Trucks: " + numberOfDispatchedTrucks);
        System.out.println("number of Packages with Normal " + numberOfPackagesGroupedByType[0]);
        System.out.println("number of Packages with Express " + numberOfPackagesGroupedByType[1]);
        System.out.println("number of Packages with Value " + numberOfPackagesGroupedByType[2]);
        System.out.println("number of dangerous Packages: " + numberOfDangerousPackages);

        PrintWriter pWriter = null;
        try {
            pWriter = new PrintWriter(new BufferedWriter(new FileWriter("report.txt")));
            pWriter.println("date: " + currentDate);
            pWriter.println("number of dispatched Trucks: " + numberOfDispatchedTrucks);
            pWriter.println("number of Packages with Normal " + numberOfPackagesGroupedByType[0]);
            pWriter.println("number of Packages with Express " + numberOfPackagesGroupedByType[1]);
            pWriter.println("number of Packages with Value " + numberOfPackagesGroupedByType[2]);
            pWriter.println("number of dangerous Packages: " + numberOfDangerousPackages);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (pWriter != null) {
                pWriter.flush();
                pWriter.close();
            }
        }
    }

    public static class Builder {
        private Date currentDate;
        private int numberOfDispatchedLKW;
        private int[] numberOfPackagesGroupedByType = new int[3];
        private int numberOfDangerousPackages;

        public Builder date() {
            this.currentDate = new Date();
            return this;
        }

        public Builder numberOfDispatchedLKW(int amount) {
            this.numberOfDispatchedLKW = amount;
            return this;
        }


        public Builder numberOfPackagesGroupedByType(int[] setNumberOfPackagesGroupedByType) {
            this.numberOfPackagesGroupedByType[0] = setNumberOfPackagesGroupedByType[0]; //Anzahl der Pakete mit NORMAL
            this.numberOfPackagesGroupedByType[1] = setNumberOfPackagesGroupedByType[1]; //Anzahl der Pakete mit EXPRESS
            this.numberOfPackagesGroupedByType[2] = setNumberOfPackagesGroupedByType[2]; // Anzahl der Pakete mit VALUE
            return this;
        }

        public Builder numberOfDangerousPackages(int amount) {
            numberOfDangerousPackages = amount;
            return this;
        }


        public Report build() {
            return new Report(this);
        }
    }
}
