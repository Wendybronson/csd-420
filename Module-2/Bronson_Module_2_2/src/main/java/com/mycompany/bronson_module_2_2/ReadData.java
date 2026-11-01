/*
 * Wendy Bronson
 * November 1, 2026
 * CSD 420 – Module 2.2
 *
 * Purpose: Read and display every saved set of five integers
 * and five doubles from the binary data file.
 */
package com.mycompany.bronson_module_2_2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class ReadData {

    public static void main(String[] args) {

        String fileName = "WendyBronson datafile.dat";

        // Check for the file before attempting to read it.
        if (!new File(fileName).exists()) {
            System.out.println("File not found. Run WriteData first.");
            return;
        }

        // "r" opens the existing file for reading only.
        try (RandomAccessFile file =
                new RandomAccessFile(fileName, "r")) {

            int setNumber = 1;

            // Continue until all saved sets have been read.
            while (file.getFilePointer() < file.length()) {

                int[] integers = new int[5];
                double[] doubles = new double[5];

                // Read values in the same order they were written.
                for (int i = 0; i < integers.length; i++) {
                    integers[i] = file.readInt();
                }

                for (int i = 0; i < doubles.length; i++) {
                    doubles[i] = file.readDouble();
                }

                System.out.println("Data set " + setNumber);
                System.out.println("Integers: "
                        + Arrays.toString(integers));
                System.out.println("Doubles:  "
                        + Arrays.toString(doubles));
                System.out.println();

                setNumber++;
            }

        } catch (IOException exception) {
            System.out.println("Unable to read the file: "
                    + exception.getMessage());
        }
    }
}