/*
 * Wendy Bronson
 * November 7, 2026
 * CSD 420 – Module 3.2 Programming Assignment
 *
 * Purpose:
 * Generate an ArrayList containing 50 random integers from 1 to 20.
 * Use a generic method to create a new ArrayList without duplicates.
 * Display both lists to demonstrate the results.
 */

package com.mycompany.bronson_module_3_2;

import java.util.ArrayList;
import java.util.Random;

public class Bronson_Module_3_2 {

    /**
     * Creates the original list and displays the results of
     * calling the generic removeDuplicates method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // Generate 50 integers in the inclusive range of 1 through 20.
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1);
        }

        // Store the new list returned by the generic method.
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        System.out.println("Wendy Bronson");
        System.out.println("CSD 420 - Module 3.2: Removing Duplicates");

        System.out.println("\nOriginal list:");
        System.out.println(originalList);
        System.out.println("Original list size: " + originalList.size());

        System.out.println("\nList without duplicates:");
        System.out.println(uniqueList);
        System.out.println("Unique list size: " + uniqueList.size());

        // Confirm that the original list still contains all 50 values.
        System.out.println("\nOriginal list size after method call: "
                + originalList.size());
    }

    /**
     * Returns a new list containing one occurrence of each value.
     * Values remain in the order in which they first appeared.
     * The original list is not modified.
     *
     * @param <E> the type of elements stored in the list
     * @param list the original list containing possible duplicates
     * @return a new ArrayList containing no duplicate values
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        // Add each value only if it is not already in the new list.
        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        return uniqueList;
    }
}