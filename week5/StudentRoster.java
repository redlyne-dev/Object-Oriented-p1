/**
 * StudentRoster.java
 * Capstone: combines ArrayList, OOP, searching, and aggregation.
 * Supports add / find / average / top-student and a List→Array snapshot.
 */
import java.util.ArrayList;

public class StudentRoster {

    /** Immutable value object — private fields, exposed via getters. */
    static class Student {
        private final String name;
        private final double grade;

        Student(String name, double grade) {
            this.name  = name;
            this.grade = grade;
        }
        String getName()  { return name;  }
        double getGrade() { return grade; }

        @Override public String toString() {
            return String.format("%s (%.1f)", name, grade);
        }
    }

    public static void main(String[] args) {
        ArrayList<Student> roster = new ArrayList<>();
        roster.add(new Student("Alice", 91.5));
        roster.add(new Student("Bob",   78.0));
        roster.add(new Student("Carol", 85.5));
        roster.add(new Student("Dave",  66.0));

        System.out.println("Roster (" + roster.size() + " students):");
        for (Student s : roster) System.out.println("  " + s);

        System.out.printf("Average grade: %.2f%n", average(roster));
        System.out.println("Top student:   " + topStudent(roster));
        System.out.println("Find 'Carol':  " + findByName(roster, "Carol"));
        System.out.println("Find 'Zoe':    " + findByName(roster, "Zoe"));

        // Fixed-size snapshot as a plain array
        Student[] snapshot = roster.toArray(new Student[0]);
        System.out.println("Snapshot length: " + snapshot.length);
    }

    static double average(ArrayList<Student> list) {
        if (list.isEmpty()) return 0.0;           // guard divide-by-zero
        double sum = 0;
        for (Student s : list) sum += s.getGrade();
        return sum / list.size();
    }

    static Student topStudent(ArrayList<Student> list) {
        if (list.isEmpty()) return null;
        Student best = list.get(0);
        for (Student s : list) {
            if (s.getGrade() > best.getGrade()) best = s;
        }
        return best;
    }

    static Student findByName(ArrayList<Student> list, String name) {
        for (Student s : list) {
            if (s.getName().equals(name)) return s;   // .equals, never ==
        }
        return null;
    }
}