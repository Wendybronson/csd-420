/**
 * Wendy Bronson
 * November 7, 2026
 * CSD 420 - Module 4.2: LinkedList Traversal
 *
 * Compares iterator traversal with get(index) for 50,000 and
 * 500,000 integers. Based on Chapter 20 and Module 4 examples.
 *
 * RESULTS:
 * With 50,000 integers, the iterator took 3.640 milliseconds,
 * while get(index) took 934.296 milliseconds.
 * With 500,000 integers, the iterator took 4.365 milliseconds,
 * while get(index) took 93,877.234 milliseconds, or about 94 seconds.
 *
 * When the list became ten times larger, the get(index) traversal
 * took about 100 times as long. The iterator time increased only
 * slightly in this run. These results showed a much larger slowdown
 * with get(index) as the list grew.
 *
 * The iterator moves through the list from one node to the next.
 * Each get(index) call starts from the closer end and follows links
 * to reach that position. Doing this for every index repeats much
 * of the work, which explains why the larger list took so long.
 *
 * Iterator traversal is O(n), while traversing the entire LinkedList
 * with repeated get(index) calls is O(n^2). My iterator times did
 * not increase tenfold, but one timing run can be affected by JVM
 * warm-up and other activity on the computer.
 *
 * Both methods returned the expected sums for both list sizes.
 * The small-list tests and the list-size checks also passed.
 */

package com.mycompany.bronson_module_4_2;

import java.util.Iterator;
import java.util.LinkedList;

public class Bronson_Module_4_2 {
    
    /** Runs correctness checks followed by both required timing tests.*/

    public static void main(String[] args) {
        System.out.println("Wendy Bronson");
        System.out.println("CSD 420 - Module 4.2: LinkedList Traversal");
        testCorrectness();
        runComparison(50_000);
        runComparison(500_000);
    }
    
    /** Builds a list containing the integers 1 through size. */
    public static LinkedList<Integer> createList(int size){
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= size; i++){
            list.add(i);
            
        }
        
        return list;
    }
    
    /** Visits every element using an explicit iterator and returns its sum. */
    public static long sumWithIterator(LinkedList<Integer> list) {
        long sum = 0;
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }
    
    /** Visits every element using get(index) and returns its sum. */
    public static long sumWithGet(LinkedList<Integer> list) {
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }
    
 /** Times traversal only; list creation and printing are outside timing. */
    public static void runComparison(int size) {
        LinkedList<Integer> list = createList(size);
        long expected = (long) size * (size + 1) / 2;
        System.out.printf("%nList size: %,d%n", size);

        long start = System.nanoTime();
        long iteratorSum = sumWithIterator(list);
        long iteratorTime = System.nanoTime() - start;

        System.out.println("Running get(index) traversal; please wait...");
        start = System.nanoTime();
        long getSum = sumWithGet(list);
        long getTime = System.nanoTime() - start;

        check(iteratorSum == expected, "Iterator sum for " + size);
        check(getSum == expected, "get(index) sum for " + size);
        check(list.size() == size, "List size unchanged");

        System.out.printf("Iterator time:   %.3f ms%n", iteratorTime / 1_000_000.0);
        System.out.printf("get(index) time: %.3f ms%n", getTime / 1_000_000.0);
        System.out.printf("Iterator sum: %,d%n", iteratorSum);
        System.out.printf("get(index) sum: %,d%n", getSum);
        System.out.printf("Expected sum: %,d%n", expected);
        System.out.println("Correctness checks: PASS");
    }
    /** Checks empty, single-element, and mixed-value lists before timing. */
    public static void testCorrectness() {
        LinkedList<Integer> list = new LinkedList<>();
        check(sumWithIterator(list) == 0 && sumWithGet(list) == 0,
                "Empty list");
        list.add(7);
        check(sumWithIterator(list) == 7 && sumWithGet(list) == 7,
                "Single element");
        list.add(-2);
        list.add(7);
        list.add(0);
        LinkedList<Integer> original = new LinkedList<>(list);
        check(sumWithIterator(list) == 12 && sumWithGet(list) == 12,
                "Negative, duplicate, and zero values");
        check(list.equals(original), "Original contents unchanged");
        System.out.println("Small-list tests: PASS");
    }

    /** Stops the program if a required check fails. */
    private static void check(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError("FAIL: " + description);
        }
    }
}

