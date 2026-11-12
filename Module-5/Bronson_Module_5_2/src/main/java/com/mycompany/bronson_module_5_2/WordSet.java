/*
 * Wendy Bronson
 * CSD420 Advanced Java Programming
 * Module 5.2 Programming Assignment
 * November 12, 2026
 * Reads words from a text file and displays unique words
 * in ascending and descending order.
 */

package com.mycompany.bronson_module_5_2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;

public class WordSet {
    public static void main(String[] args) {
        Path file = Path.of("collection_of_words.txt");

        try {
            NavigableSet<String> words = readWords(file);

            System.out.println("Words in ascending order:");
            displayWords(words);

            System.out.println("\nWords in descending order:");
            displayWords(words.descendingSet());
        } catch (IOException e) {
            System.out.println("Could not read collection_of_words.txt");
            e.printStackTrace();
        }
    }

    static NavigableSet<String> readWords(Path file) throws IOException {
        NavigableSet<String> words = new TreeSet<>();

        try (Scanner input =
                 new Scanner(Files.newBufferedReader(file, StandardCharsets.UTF_8))) {
            input.useDelimiter("[^\\p{L}\\p{N}]+");

            while (input.hasNext()) {
                String word = input.next().toLowerCase(Locale.ROOT);
                if (!word.isBlank()) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    static void displayWords(Iterable<String> words) {
        for (String word : words) {
            System.out.println(word);
        }
    }
}