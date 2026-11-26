/**
 * ArrayAlgorithms.java
 * Common array algorithms: linear search, min/max, in-place reverse, sort.
 * These are recurring exam building blocks — learn the loop invariants.
 */
import java.util.Arrays;

public class ArrayAlgorithms {
    public static void main(String[] args) {
        int[] data = {42, 7, 19, 88, 3, 56, 21};

        System.out.println("Original: " + Arrays.toString(data));
        System.out.println("Max: " + findMax(data));
        System.out.println("Index of 88: " + linearSearch(data, 88));
        System.out.println("Index of 99: " + linearSearch(data, 99));

        reverseInPlace(data);
        System.out.println("Reversed: " + Arrays.toString(data));

        Arrays.sort(data); // O(n log n) dual-pivot quicksort for primitives
        System.out.println("Sorted: " + Arrays.toString(data));
    }

    /** Linear search: O(n). Returns index, or -1 if the target is absent. */
    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;   // early exit on first match
        }
        return -1;
    }

    /** Find max: O(n), single pass. Assumes non-empty input. */
    static int findMax(int[] arr) {
        int max = arr[0];                     // seed with first element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    /**
     * Reverse in place: O(n) time, O(1) extra space.
     * Two-pointer swap of symmetric pairs — no second array needed.
     */
    static void reverseInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left++]  = arr[right];
            arr[right--] = tmp;
        }
    }
}