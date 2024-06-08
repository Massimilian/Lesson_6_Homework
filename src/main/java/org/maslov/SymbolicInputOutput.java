package org.maslov;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SymbolicInputOutput implements InputOutput {

    @Override
    public void findWordsFromVocals(String address) {
        List<String> list = readString(address);
        String[] words = prepareArrayWithWords(list);
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                char ch = words[i].charAt(0);
                if ("aeiouAEIOU".contains(String.valueOf(ch))) {
                    System.out.println(words[i]);
                    break;
                }
            }
        }
    }


    @Override
    public void findWordPairs(String address) {
        List<String> list = readString(address);
        String[] words = prepareArrayWithWords(list);
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i] != null && words[i].length() > 0 && words[i + 1] != null && words[i + 1].length() > 0 &&
                    words[i].charAt(words[i].length() - 1) == words[i + 1].charAt(0)) {
                System.out.println(String.format("%s - %s", words[i], words[i + 1]));
            }
        }
    }

    @Override
    public void findMaxNumbers(String address) {
        List<String> list = readString(address);
        findMaxNumbersIntoArray(list);
    }

    @Override
    public void repareJavaCode(String address) {
        List<String> list = readString(address);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(prepareNewAddress(address))));
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).replaceAll("public", "private"));
                bw.write("\r\n");
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void returnJavaCode(String address) {
        List<String> list = readString(address);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(prepareNewAddress(address))));
            for (int i = 0; i < list.size(); i++) {
                bw.write(new StringBuilder(list.get(i)).reverse().toString());
                bw.write("\r\n");
            }
            bw.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private List<String> readString(String address) {
        List<String> lines = new ArrayList<String>();
        try (FileReader fr = new FileReader(address)) {
            BufferedReader br = new BufferedReader(fr);
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


    private String[] prepareArrayWithWords(List<String> list) {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] temp = list.get(i).split(" ");
            for (int j = 0; j < temp.length; j++) {
                words.add(temp[j]);
            }
        }
        String[] result = new String[words.size()];
        result = words.toArray(result);
        return result;
    }

    private void findMaxNumbersIntoArray(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            printMaxNums(list.get(i), i);
        }
    }

    private void printMaxNums(String s, int num) {
        int max = 0;
        int finder = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                finder++;
            } else {
                if (max < finder) {
                    max = finder;
                }
                finder = 0;
            }
        }
        System.out.printf("Строка №%d. Количество цифр подряд: %d.%s", num + 1, Math.max(max, finder), System.lineSeparator());
    }

    private String prepareNewAddress(String address) {
        int index = address.lastIndexOf("/");
        return String.format("%snew%s", address.substring(0, index + 1), address.substring(index + 1, address.length()));
    }
}
