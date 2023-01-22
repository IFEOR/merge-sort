package org.ifeor;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Reader {

    Reader(String[] args) {
        this.args = args;
        this.explicitSortMode = checkExplicitSortMode();
        this.inputFiles = explicitSortMode ? args.length - 3 : args.length - 2;
    }

    private final String[] args;
    private final boolean explicitSortMode;
    private final int inputFiles;

    public int getInputFiles() {
        return inputFiles;
    }

    private boolean checkExplicitSortMode() {
        return args[0].equals(SortMode.ascending) || args[0].equals(SortMode.descending);
    }

    public String readInputFileName(int fileNumber) {
        return explicitSortMode ? args[3 + fileNumber] : args[2 + fileNumber];
    }

    public String readSortMode() {
        if (explicitSortMode)
            return args[0];
        return SortMode.ascending;
    }

    public String readDataType() {
        switch (explicitSortMode ? args[1] : args[0]) {
            case (DataType.number):
                return DataType.number;
            case (DataType.string):
                return DataType.string;
        }
        return null;
    }

    public String readOutFileName() {
        try {
            return explicitSortMode ? args[2] : args[1];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public String[] readStrFile(String fileName) {
        List<String> fileContent = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка: не удаётся прочитать файл " + fileName);
            e.printStackTrace();
        }
        return fileContent.toArray(new String[0]);
    }

    public int[] readNumFile(String fileName) {
        List<Integer> fileContent = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка: не удаётся прочитать файл " + fileName);
            e.printStackTrace();
        }
        return fileContent.stream().mapToInt(i -> i).toArray();
    }
}
