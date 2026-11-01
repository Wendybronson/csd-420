/*
 * Wendy Bronson
 * November 1, 2026
 * CSD 420 – Module 2.2
 *
 * Purpose: Generate five random integers and five random doubles,
 * then append both arrays to a binary data file.
 */
package com.mycompany.bronson_module_2_2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Random;

public class WriteData {

    public static void main(String[] args) {

        String fileName = "WendyBronson datafile.dat";

        int[] integers = new int[5];
        double[] doubles = new double[5];
        Random random = new Random();

        // Fill both arrays with random values.
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(100) + 1; // 1 through 100
            doubles[i] = random.nextDouble() * 100; // 0 up to 100
        }

        // "rw" creates the file if it does not already exist.
        try (RandomAccessFile file =
                new RandomAccessFile(fileName, "rw")) {

            // Move to the end so previous data is preserved.
            file.seek(file.length());

            // Save the five integers first.
            for (int value : integers) {
                file.writeInt(value);
            }

            // Save the five doubles after the integers.
            for (double value : doubles) {
                file.writeDouble(value);
            }

            System.out.println("Data appended to: " + fileName);
            System.out.println("Integers: " + Arrays.toString(integers));
            System.out.println("Doubles:  " + Arrays.toString(doubles));
            System.out.println("File size: " + file.length() + " bytes");

        } catch (IOException exception) {
            System.out.println("Unable to write the file: "
                    + exception.getMessage());
        }
    }
}