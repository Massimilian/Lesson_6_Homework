package org.maslov;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ByteInputOutput implements InputOutput{

    @Override
    public void findWordsFromVocals(String address) {
        String[] words = readWords(address, new String[]{" ", "\\Q\r\n\\E"});
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
        String[] words = readWords(address, new String[]{" ", "\\Q\r\n\\E"});
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].length() > 0) {
                if (words[i].charAt(words[i].length() - 1) == words[i + 1].charAt(0)) {
                    System.out.printf("%s - %s%s", words[i], words[i + 1], System.lineSeparator());
                }
            }
        }
    }

    @Override
    public void findMaxNumbers(String address) {
        String[] strings = readWords(address, new String[]{"\\Q\r\n\\E"});
        for (int i = 0; i < strings.length; i++) {
            System.out.printf("№%d. '", i + 1);
            findMaxNums(strings[i]);
            System.out.println("'");
        }
    }


    @Override
    public void repareJavaCode(String address) {
        String str = readString(address).replaceAll("public", "private");
        writeIntoFile(address, str);
    }


    @Override
    public void returnJavaCode(String address) {
        String[] strings = readWords(address, new String[]{"\\Q\r\n\\E"});
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(new StringBuilder(strings[i]).reverse().toString());
            sb.append("\r\n");
        }
        String result = sb.substring(0, sb.length() - 3);
        writeIntoFile(address, result);
    }

    private void writeIntoFile(String address, String str) {
        String newAddress = prepareNewAddress(address);
        Path path = Path.of(newAddress);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(newAddress)));
                dos.writeBytes(str);
                dos.flush();
                dos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String prepareNewAddress(String address) {
        int index = address.lastIndexOf("/");
        return String.format("%snew%s", address.substring(0, index + 1), address.substring(index + 1, address.length()));
    }

    private String readString(String address) {
        DataInputStream dis = null;
        StringBuilder sb = new StringBuilder();
        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(address)));
            while (true) {
                sb.append((char) dis.readByte());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return sb.toString();
        }
    }

    private String[] readWords(String address, String[] symbols) {
        return readString(address).split(prepareRegex(symbols));
    }

    private String prepareRegex(String[] symbols) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < symbols.length; i++) {
            sb.append(symbols[i]);
        }
        sb.append("]+");
        return sb.toString();
    }


    private void findMaxNums(String string) {
        int pos = 0;
        int count = 0;
        int maxCount = 0;
        boolean isNear = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                if (!isNear) {
                    pos = i;
                }
                isNear = true;
            } else {
                isNear = false;
                if (count > maxCount) {
                    maxCount = count;
                }
                count = 0;
            }
            if (isNear) {
                count++;
            }
        }
        if (!isNear) {
            for (int i = pos; i < pos + maxCount; i++) {
                System.out.print(string.charAt(i));
            }
        } else {
            for (int i = string.length() - maxCount - 1; i < string.length(); i++) {
                System.out.print(string.charAt(i));
            }
        }
    }
}
