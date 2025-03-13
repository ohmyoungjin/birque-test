package biz.brique.coding.test.one;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class CsvFileRead {
    private static final String FILE_ID = "1Ah0gkauGCIqJHpFGhTgsEZCjYFRscjTh";
    private static final String DOWNLOAD_URL = "https://drive.google.com/uc?export=download&id=" + FILE_ID;

    public void csvRead() {
        try {
            File file = downloadCsv(DOWNLOAD_URL);
            processCsv(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File downloadCsv(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download file: " + connection.getResponseMessage());
        }

        File tempFile = File.createTempFile("downloaded", ".csv");
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(tempFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        return tempFile;
    }



    public void processCsv(File file) throws IOException {
        List<List<String>> invalidValues = new ArrayList<>();
        int totalCount = 0;      // 전체 숫자 개수
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {
                List<Double> numbers = new ArrayList<>();
                List<String> nonNumericValues = new ArrayList<>();
                for (String value : record.values()) {
                    try {
                        numbers.add(Double.parseDouble(value));
                    } catch (NumberFormatException e) {
                        nonNumericValues.add(value);
                    }
                }
                if (nonNumericValues.isEmpty()) {
                    printStatistics(numbers);
                } else {
                    invalidValues.add(nonNumericValues);
                }
                totalCount++;
            }
        }
        printResult(totalCount, invalidValues); // 결과 출력
    }

    private void printStatistics(List<Double> numbers) {
        double[] data = numbers.stream().mapToDouble(Double::doubleValue).toArray();
        double min = StatUtils.min(data);
        double max = StatUtils.max(data);
        double sum = StatUtils.sum(data);
        double avg = StatUtils.mean(data);
        double stdDev = Math.sqrt(StatUtils.variance(data));
        double median = new Median().evaluate(data);
        System.out.printf("Min: %.2f, Max: %.2f, Sum: %.2f, avg: %.2f, Std Dev: %.2f, Median: %.2f%n",
                min, max, sum, avg, stdDev, median);
    }

    private void printResult(int totalCount, List<List<String>> invalidValues) {
        System.out.println("------------------------------------");
        System.out.printf("The total number of lines: %d", totalCount);
        System.out.println();
        System.out.printf("The calculated lines: %d", (totalCount - invalidValues.size()));
        System.out.println();
        if (!invalidValues.isEmpty()) {
            System.out.println("The error values:");
            for (List<String> values : invalidValues) {
                System.out.print(String.join("", values));
                //System.out.print(values);
            }
        }
    }
}
