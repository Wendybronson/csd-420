/*
 * Wendy Bronson
 * November 22, 2026
 * CSD 420 - Module 6.2 Programming Assignment
 * Generic bubble sort using Comparable and Comparator
 */
package com.mycompany.bronson_module_6_2;

import java.util.Arrays;
import java.util.Comparator;

public class Bronson_Module_6_2 {

    /**
     * Sorts an array in place using the elements' natural order.
     *
     * @param <E> the type of elements that can compare themselves
     * @param list the array to sort
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        bubbleSort(list, Comparable::compareTo);
    }

    /**
     * Sorts an array in place using the supplied ordering rule.
     *
     * @param <E> the type of elements in the array
     * @param list the array to sort
     * @param comparator the rule for comparing two elements
     */
    public static <E> void bubbleSort(E[] list,
            Comparator<? super E> comparator) {
        for (int last = list.length - 1; last > 0; last--) {
            boolean swapped = false;

            // Compare neighbors and move the last item into place.
            for (int index = 0; index < last; index++) {
                if (comparator.compare(list[index], list[index + 1]) > 0) {
                    E current = list[index];
                    list[index] = list[index + 1];
                    list[index + 1] = current;
                    swapped = true;
                }
            }

            // If nothing moved during this pass, the array is sorted.
            if (!swapped) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Module 6.2 - Generic Bubble Sort");
        System.out.println();

        Integer[] scores = {88, 92, 75, 81, 75};
        System.out.println("Original scores: " + Arrays.toString(scores));
        bubbleSort(scores);
        checkOrder("Comparable - ascending scores", scores,
                new Integer[]{75, 75, 81, 88, 92});

        String[] names = {"Morgan", "Jamie", "Casey"};
        bubbleSort(names);
        checkOrder("Comparable - alphabetical names", names,
                new String[]{"Casey", "Jamie", "Morgan"});

        Integer[] quantities = {25, 10, 50, 15};
        System.out.println("Original quantities: " + Arrays.toString(quantities));
        bubbleSort(quantities, Comparator.reverseOrder());
        checkOrder("Comparator - descending quantities", quantities,
                new Integer[]{50, 25, 15, 10});

        // The comparator puts the highest priority first and breaks ties by title.
        Task restore = new Task("Restore access", 3);
        Task update = new Task("Update profile", 1);
        Task login = new Task("Fix login", 3);
        Task[] tasks = {restore, update, login};

        Comparator<Task> byPriority = Comparator.comparingInt(Task::priority)
                .reversed()
                .thenComparing(Task::title);

        bubbleSort(tasks, byPriority);
        checkOrder("Comparator - task priority and title", tasks,
                new Task[]{login, restore, update});

        Integer[] empty = {};
        bubbleSort(empty);
        checkOrder("Comparable - empty array", empty, new Integer[]{});

        Integer[] single = {42};
        bubbleSort(single, Comparator.reverseOrder());
        checkOrder("Comparator - one element", single, new Integer[]{42});

        Integer[] sorted = {1, 2, 3};
        bubbleSort(sorted);
        checkOrder("Comparable - already sorted", sorted,
                new Integer[]{1, 2, 3});

        System.out.println();
        System.out.println("All seven tests passed.");
    }

    /**
     * Checks the sorted array against the expected result.
     */
    private static <E> void checkOrder(String description, E[] actual,
            E[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(description
                    + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }

        System.out.println("PASS: " + description + " " + Arrays.toString(actual));
    }

    private record Task(String title, int priority) {
        @Override
        public String toString() {
            return title + " (priority " + priority + ")";
        }
    }
}