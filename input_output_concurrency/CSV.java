package input_output_concurrency;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSV {
    // Tu zapisane są parametry obiektu, które będą wykorzystane w programie
    String inputpath;
    String inseparator;
    String outputpath;
    String outseparator;
    List<String[]> csv;

    void csvToString(){
        for (String[] table : this.csv) {
            System.out.println(Arrays.toString(table));
        }
    }
    @Override
    public String toString() {
        System.out.println("Zawartość danych obiektu input_output_concurrency.CSV:");
        csvToString();
        return "pozostałe parametry input_output_concurrency.CSV{" +
                "inputpath='" + inputpath + '\'' +
                ", inseparator='" + inseparator + '\'' +
                ", outputpath='" + outputpath + '\'' +
                ", outseparator='" + outseparator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSV csv1 = (CSV) o;

        if (!getInputpath().equals(csv1.getInputpath())) return false;
        if (!getInseparator().equals(csv1.getInseparator())) return false;
        if (!getOutputpath().equals(csv1.getOutputpath())) return false;
        if (!getOutseparator().equals(csv1.getOutseparator())) return false;
        return getCsv().equals(csv1.getCsv());
    }

    @Override
    public int hashCode() {
        int result = getInputpath().hashCode();
        result = 31 * result + getInseparator().hashCode();
        result = 31 * result + getOutputpath().hashCode();
        result = 31 * result + getOutseparator().hashCode();
        result = 31 * result + getCsv().hashCode();
        return result;
    }

    public String getInputpath() {
        return inputpath;
    }

    public void setInputpath(String inputpath) {
        this.inputpath = inputpath;
    }

    public String getInseparator() {
        return inseparator;
    }

    public void setInseparator(String inseparator) {
        this.inseparator = inseparator;
    }

    public String getOutputpath() {
        return outputpath;
    }

    public void setOutputpath(String outputpath) {
        this.outputpath = outputpath;
    }

    public String getOutseparator() {
        return outseparator;
    }

    public void setOutseparator(String outseparator) {
        this.outseparator = outseparator;
    }

    public List<String[]> getCsv() {
        return csv;
    }

    public void setCsv(List<String[]> csv) {
        this.csv = csv;
    }

    public CSV(String inputpath, String inseparator, String outputpath, String outseparator) {
        this.inputpath = inputpath;
        this.outputpath = outputpath;
        this.inseparator = inseparator;
        this.outseparator = outseparator;
        {
        try {
           this.csv = this.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public List<String[]> read() throws IOException {
        BufferedReader br = null;
        List<String[]> csv = new ArrayList<>();
        try {
            File inputfile = new File(inputpath);
            br = new BufferedReader(new FileReader(inputfile));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(inseparator);
                csv.add(values);
            }
            return csv;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public String toCSV(String[] data) {
        return Stream.of(data)
                .map(this::skipSpecialChars)
                .collect(Collectors.joining(outseparator));
    }

    public void write() throws IOException {
        File csvOutputFile = new File(this.outputpath);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            this.csv.stream()
                    .map(this::toCSV)
                    .forEach(pw::println);
        }
        if ((!csvOutputFile.exists())) throw new AssertionError();
    }

    public String skipSpecialChars(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public String getValue(int row, int column) {
        return this.csv.get(row-1)[column-1];
    }
}
