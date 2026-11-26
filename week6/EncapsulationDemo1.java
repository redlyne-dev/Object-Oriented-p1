/**
 * EncapsulationDemo1.java
 *
 * Single-file demonstration of encapsulation: public fields (BadStudent)
 * versus private fields with validating setters (GoodStudent).
 *
 * File-naming rule: only ONE class per file may be public, and it must
 * match the filename. So EncapsulationDemo1 is public; the two helper
 * classes below it are package-private.
 */
public class EncapsulationDemo1 {

    public static void main(String[] args) {

        // ---------- BadStudent: state corrupted at will ----------
        System.out.println("=== BAD STUDENT (no encapsulation) ===");

        BadStudent bad = new BadStudent();
        bad.name = "Alice";
        bad.age  = -100;   // accepted silently — no validation
        bad.gpa  = 15.5;   // accepted silently — no validation

        System.out.println("Name: " + bad.name);
        System.out.println("Age:  " + bad.age + "  (corrupted)");
        System.out.println("GPA:  " + bad.gpa + " (corrupted)");

        // ---------- GoodStudent: validated state ----------
        System.out.println("\n=== GOOD STUDENT (encapsulated) ===");

        GoodStudent good = new GoodStudent("Bob", 20, 3.5);
        System.out.println("Name: " + good.getName());
        System.out.println("Age:  " + good.getAge());
        System.out.println("GPA:  " + good.getGpa());

        // Invalid updates are rejected; the object's state is unchanged.
        System.out.println("\nAttempting invalid updates...");
        good.setAge(-100);   // rejected by setter
        good.setGpa(15.5);   // rejected by setter

        System.out.println("Age after attempt: " + good.getAge());
        System.out.println("GPA after attempt: " + good.getGpa());
    }
}


/**
 * BadStudent — ANTI-PATTERN EXAMPLE.
 * Public fields expose state to every class with no validation,
 * so illegal values (age = -100, GPA = 15.5) can be assigned freely.
 */
class BadStudent {

    // Public fields: no encapsulation, no validation, no control.
    public String name;
    public int age;
    public double gpa;
}


/**
 * GoodStudent — ENCAPSULATION EXAMPLE.
 * Fields are private; access is mediated by getters and setters.
 * Setters validate input, so an instance can never hold an illegal
 * state (age outside 0..150, GPA outside 0.0..4.0).
 */
class GoodStudent {

    private String name;
    private int age;
    private double gpa;

    /**
     * Routes initial values through the validating setters so the
     * constructor cannot create an invalid object either.
     */
    public GoodStudent(String name, int age, double gpa) {
        this.name = name;   // 'this' disambiguates field vs. parameter
        setAge(age);
        setGpa(gpa);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    /** Rejects ages outside the realistic range 0..150. */
    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("Error: Invalid age. Age must be between 0 and 150.");
        }
    }

    public double getGpa() {
        return gpa;
    }

    /** Rejects GPAs outside the valid range 0.0..4.0. */
    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Error: Invalid GPA. GPA must be between 0.0 and 4.0.");
        }
    }
}