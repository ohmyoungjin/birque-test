package biz.brique.coding.test.one;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class CsvFileRead {

    public void csvRead(String downloadUrl) {
        try {
            File file = downloadCsv(downloadUrl);
            processCsv(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Csv download function */
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
        List<List<String>> invalidValues = new ArrayList<>(); /* 숫자가 아닌 값 넣는 List */
        int totalCount = 0;      /* 전체 숫자 개수 */
        try (Reader reader = new FileReader(file, StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) /* 첫행에 대하여 읽지 않는다.*/
        {
            for (CSVRecord record : csvParser) {
                List<Double> numbers = new ArrayList<>(); /* 숫자 넣는 List*/
                List<String> nonNumericValues = new ArrayList<>(); /* 숫자가 아닌 값 넣는 List */
                for (String value : record.values()) {
                    try {
                        numbers.add(Double.parseDouble(value));
                    } catch (NumberFormatException e) {
                        nonNumericValues.add(value); /* 숫자가 아닌 값은 NumberFormatException 나오므로, 숫자가 아닌 List에 담는다. */
                    }
                }
                if (nonNumericValues.isEmpty()) { /* 숫자가 아닌 값이 한 개라도 껴있는 row는 계산하지 않는다*/
                    printStatistics(numbers);
                } else {
                    invalidValues.add(nonNumericValues);
                }
                totalCount++;
            }
        }
        printResult(totalCount, invalidValues); // 결과 출력
    }

    /* 한 Row 에 대하여 여러가지 계산해주는 함수 */
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

    /* 결과값 도출 함수*/
    private void printResult(int totalCount, List<List<String>> invalidValues) {
        System.out.println("------------------------------------");
        System.out.printf("The total number of lines: %d", totalCount);
        System.out.println();
        System.out.printf("The calculated lines: %d", (totalCount - invalidValues.size())); /* 총 숫자 에서 숫자가 아닌 리스트 값을 빼줘서 실제 계산한 row 수 구해준다 */
        System.out.println();
        if (!invalidValues.isEmpty()) {
            System.out.println("The error values:");
            for (List<String> values : invalidValues) {
                System.out.print(String.join("", values));
            }
        }
    }
}
