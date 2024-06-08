package org.maslov;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SymbolicInputOutput bio = new SymbolicInputOutput();
        String address = "D:/progwards/Lesson6Homework/src/main/resources/text.txt";
        bio.findWordsFromVocals(address);
        bio.findWordPairs(address);
        bio.findMaxNumbers(address);
        bio.returnJavaCode(address);
    }
}
