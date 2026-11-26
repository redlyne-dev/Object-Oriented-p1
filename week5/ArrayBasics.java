/**
 * ArrayBasics.java
 * Demonstrates declaration, initialization, and iteration of Java arrays.
 * Key facts: arrays are fixed-size, zero-indexed, and homogeneous.
 */
public class ArrayBasics {
    public static void main(String[] args) {
        // Declaration + allocation: size is fixed at 5, elements default to 0
        int[] scores = new int[5];

        // Element assignment by index (0..length-1)
        scores[0] = 85;
        scores[1] = 92;
        scores[2] = 78;
        scores[3] = 91;
        scores[4] = 88;

        // Array literal: convenient when values are known upfront
        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri"};

        // Enhanced for-loop: cleanest way to READ every element
        // (cannot modify the array via the loop variable)
        System.out.println("Scores:");
        for (int s : scores) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Classic for-loop: needed when the index itself matters
        System.out.println("Weekdays:");
        for (int i = 0; i < weekdays.length; i++) {
            System.out.println("  [" + i + "] " + weekdays[i]);
        }

        // length is a FIELD (no parentheses), unlike String.length()
        System.out.println("Number of scores: " + scores.length);
    }
}