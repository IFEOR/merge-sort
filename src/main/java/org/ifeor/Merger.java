package org.ifeor;

import java.util.Arrays;

public class Merger {

    public static String[] mergeSort(int[] firstArr, int[] secondArr, boolean descending) {
        int[] mergedArr = new int[firstArr.length + secondArr.length];
        int i = 0, j = 0, buffer;
        for (int n = 0; n < mergedArr.length; n++) {
            if (i > firstArr.length - 1) {
                buffer = secondArr[j++];
            } else if (j > secondArr.length - 1) {
                buffer = firstArr[i++];
            } else if (!descending && firstArr[i] < secondArr[j] || descending && firstArr[i] > secondArr[j]) {
                buffer = firstArr[i++];
            } else {
                buffer = secondArr[j++];
            }
            mergedArr[n] = buffer;
        }
        return Arrays.stream(mergedArr).mapToObj(String::valueOf).toArray(String[]::new);
    }

    public static String[] mergeSort(String[] firstArr, String[] secondArr, boolean descending) {
        String[] mergedArr = new String[firstArr.length + secondArr.length];
        int i = 0, j = 0;
        String buffer;
        for (int n = 0; n < mergedArr.length; n++) {
            if (i > firstArr.length - 1) {
                buffer = secondArr[j++];
            } else if (j > secondArr.length - 1) {
                buffer = firstArr[i++];
            } else if (!descending && firstCharIsLess(firstArr[i].toCharArray(), secondArr[j].toCharArray())
                    || descending && firstCharIsLess(secondArr[j].toCharArray(), firstArr[i].toCharArray())) {
                buffer = firstArr[i++];
            } else {
                buffer = secondArr[j++];
            }
            mergedArr[n] = buffer;
        }
        return mergedArr;
    }

    public static boolean firstCharIsLess(char[] first, char[] second) {
        for (int n = 0; n < Math.min(first.length, second.length); n++) {
            if (first[n] != second[n])
                return first[n] < second[n];
        }
        return first.length < second.length;
    }
}
