package org.ifeor;

public class Main {

    static Reader reader;
    static String sortMode = SortMode.ascending;
    static String dataType;
    static String outFile;
    static String[] mergedArr;

    public static void main(String[] args) {
        readArgs(args);
        if (isValidConsoleInput() && Writer.createFile(outFile))
            mergeSort();
    }

    public static void readArgs(String[] args) {
        reader = new Reader(args);
        sortMode = reader.readSortMode();
        dataType = reader.readDataType();
        outFile = reader.readOutFileName();
    }

    public static boolean isValidConsoleInput() {
        if (dataType == null) {
            System.out.println("Ошибка: не указан тип данных (-i / -s)");
            return false;
        }
        if (outFile == null) {
            System.out.println("Ошибка: не указано имя выходного файла (например, out.txt)");
            return false;
        }
        if (reader.getInputFiles() < 1) {
            System.out.println("Ошибка: не указано имя входного файла (например, in.txt)");
            return false;
        }
        return true;
    }

    public static void mergeSort() {
        for (int i = 0; i < reader.getInputFiles(); i++) {
            mergedArr = dataType.equals(DataType.string) ?
                    Merger.mergeSort(reader.readStrFile(reader.readInputFileName(i)), reader.readStrFile(outFile), sortMode.equals(SortMode.descending)) :
                    Merger.mergeSort(reader.readNumFile(reader.readInputFileName(i)), reader.readNumFile(outFile), sortMode.equals(SortMode.descending));
            if (Writer.writeFile(outFile, mergedArr))
                System.out.println("Сортировка слиянием прошла успешно! Результат записан в файл " + outFile);
        }
    }
}
