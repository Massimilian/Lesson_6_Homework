package org.maslov;

import java.io.IOException;

public interface InputOutput {

    /**
     * Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
     *
     * @param address адрес файла для чтения
     * @throws IOException если файл не найден
     */
    public void findWordsFromVocals(String address);

    /**
     * Задан файл с текстом, найти и вывести в консоль все слова,
     * для которых последняя буква одного слова совпадает с первой буквой следующего слова
     */
    public void findWordPairs(String address);

    /**
     * Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
     *
     * @param address
     */
    public void findMaxNumbers(String address);

    /**
     * 4. Задан файл с java-кодом.
     * Прочитать текст программы из файла и все слова public в объявлении атрибутов и методов класса заменить на слово private.
     * Результат сохранить в другой заранее созданный файл.
     */
    public void repareJavaCode(String address) throws IOException;


    /**
     * 5. Задан файл с java-кодом. Прочитать текст программы из файла и записать в другой файл в обратном порядке символы каждой строки.
     *
     * @param address
     */
    public void returnJavaCode(String address);

}
