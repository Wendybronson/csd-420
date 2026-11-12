/*
 * Wendy Bronson
 * CSD420 Advanced Java Programming
 * Module 5.2 Programming Assignment
 * November 12, 2026
 * Testing the word-reading, duplicate-
 * removal, and ordering behavior.
 */
package com.mycompany.bronson_module_5_2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;

public class WordSetTest {
    public static void main(String[] args) throws Exception {
        Path testFile = Files.createTempFile("word-test", ".txt");

        try {
            Files.writeString(
                testFile,
                "Pear apple APPLE banana pear.",
                StandardCharsets.UTF_8
            );

            NavigableSet<String> words = WordSet.readWords(testFile);

            List<String> ascending = new ArrayList<>(words);
            List<String> descending =
                new ArrayList<>(words.descendingSet());

            if (!ascending.equals(List.of("apple", "banana", "pear"))) {
                throw new AssertionError("Ascending order or duplicate removal failed.");
            }

            if (!descending.equals(List.of("pear", "banana", "apple"))) {
                throw new AssertionError("Descending order failed.");
            }

            System.out.println("All tests passed.");
        } finally {
            Files.deleteIfExists(testFile);
        }
    }
}