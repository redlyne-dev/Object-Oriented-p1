/**
 * ArrayListBasics.java
 * ArrayList<E> is a resizable, array-backed list from java.util.
 * Unlike arrays, it grows/shrinks automatically and holds OBJECTS only.
 */
import java.util.ArrayList;
import java.util.Collections;

public class ArrayListBasics {
    public static void main(String[] args) {
        // Diamond operator <> infers the generic type.
        // Primitives must be auto-boxed (Integer, Double, ...).
        ArrayList<String> names = new ArrayList<>();

        names.add("Alice");        // append at end — O(1) amortized
        names.add("Bob");
        names.add("Carol");
        names.add(1, "Zara");      // insert at index 1 — O(n) shift right

        System.out.println("Size: " + names.size());   // method, NOT a field
        System.out.println("At index 0: " + names.get(0));
        System.out.println("All: " + names);

        names.set(2, "Bobby");     // replace element
        names.remove("Bob");       // remove by VALUE
        System.out.println("After edits: " + names);

        System.out.println("Contains 'Alice'? " + names.contains("Alice"));

        Collections.sort(names);   // natural ordering
        System.out.println("Sorted: " + names);

        // For-each works identically to arrays
        for (String n : names) System.out.println("  -> " + n);
    }
}