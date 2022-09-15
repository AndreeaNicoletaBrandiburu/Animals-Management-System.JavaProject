package main.csv;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public final class WorkWithCsv {

    private static final List<WorkWithCsv> workWithCsvFileList = new LinkedList<>();
    private final String fileName;

    private WorkWithCsv(String fileName) {
        this.fileName = fileName;
        //Create file
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("The file with this name " + fileName + " could not be created");
        }
    }

    public String readLineFromFile(int lineNumber) {
        String finalLine =  null;
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null && lineNumber > 0) {
                lineNumber--;
                finalLine = line;
            }
            if(lineNumber != 0) {
                return null;
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found" + fileName);
        } catch (IOException e) {
            System.err.println("There was a problem reading from file " + fileName);
        }
        return finalLine;
    }

    private static Optional<WorkWithCsv> searchInstanceForFileName(String fileName) {
        return workWithCsvFileList.stream().filter(workWithCsvFileCat -> workWithCsvFileCat.fileName.equals(fileName)).findAny();
    }

    public static WorkWithCsv getInstance(String fileName) {
        return searchInstanceForFileName(fileName).orElse(new WorkWithCsv(fileName));

    }

    public void writeLineInFile(String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(line);
        } catch (IOException e) {
            System.err.println("An error occurred while writing in file " + fileName);
        }
    }
}
