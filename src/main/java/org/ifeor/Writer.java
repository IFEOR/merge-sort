package org.ifeor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean createFile(String fileName) {

        File file = new File(fileName);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Не удаётся создать файл " + fileName);
            return false;
        }
        return true;
    }

    public static boolean writeFile(String fileName, String[] mergedArr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String i : mergedArr) {
                bw.write(i);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Не удаётся записать файл " + fileName);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
