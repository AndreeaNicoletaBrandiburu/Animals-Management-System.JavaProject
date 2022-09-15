package main.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogUtils {
    public static void addLogToFile(String method, LocalDateTime localDateTime, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(method + "," + localDateTime);
        } catch (IOException e) {
            System.err.println("There was an error while writing in the file " + fileName);
        }
    }
}
