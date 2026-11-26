import java.util.Scanner;

/**
 * Object-oriented area calculator.
 *
 * Each shape is a class that implements the Shape interface.
 * The Shape interface declares area(), perimeter(), and name().
 * Polymorphism lets us treat all shapes the same way in printShape().
 */
public class OopAreaCalc {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(in, "Choose a shape: ");
            Shape shape = null;

            switch (choice) {
                case 1: {
                    double r = readDouble(in, "Radius: ");
                    shape = new Circle(r);
                    break;
                }
                case 2: {
                    double w = readDouble(in, "Width:  ");
                    double h = readDouble(in, "Height: ");
                    shape = new Rectangle(w, h);
                    break;
                }
                case 3: {
                    double b = readDouble(in, "Base:   ");
                    double h = readDouble(in, "Height: ");
                    shape = new TriangleBaseHeight(b, h);
                    break;
                }
                case 4: {
                    double a = readDouble(in, "Side a: ");
                    double b = readDouble(in, "Side b: ");
                    double c = readDouble(in, "Side c: ");
                    try {
                        shape = new TriangleSides(a, b, c);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Those sides do not form a triangle.");
                    }
                    break;
                }
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Please choose 0-4.");
            }

            if (shape != null) {
                printShape(shape);
            }
            System.out.println();
        }

        in.close();
        System.out.println("Bye.");
    }

    /** Polymorphic method: works with any Shape. */
    private static void printShape(Shape shape) {
        System.out.printf("%s area = %.4f", shape.name(), shape.area());
        try {
            double p = shape.perimeter();
            System.out.printf("   perimeter = %.4f%n", p);
        } catch (UnsupportedOperationException e) {
            System.out.println(); // perimeter not available for this shape
        }
    }

    private static void printMenu() {
        System.out.println("--- Area calculator (OOP) ---");
        System.out.println("1) Circle");
        System.out.println("2) Rectangle");
        System.out.println("3) Triangle (base x height)");
        System.out.println("4) Triangle (three sides)");
        System.out.println("0) Quit");
    }

    private static int readInt(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Not a whole number, try again.");
            }
        }
    }

    private static double readDouble(Scanner in, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Not a number, try again.");
            }
        }
    }
}

// ---------------------------------------------------------------------------
// Shape interface
// ---------------------------------------------------------------------------

interface Shape {
    double area();
    double perimeter();
    String name();
}

// ---------------------------------------------------------------------------
// Concrete shape classes
// ---------------------------------------------------------------------------

class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
        this.radius = radius;
    }

    @Override public double area()      { return Math.PI * radius * radius; }
    @Override public double perimeter() { return 2 * Math.PI * radius; }
    @Override public String name()      { return "Circle"; }
}

class Rectangle implements Shape {
    private final double width, height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("Dimensions must be positive");
        this.width = width;
        this.height = height;
    }

    @Override public double area()      { return width * height; }
    @Override public double perimeter() { return 2 * (width + height); }
    @Override public String name()      { return "Rectangle"; }
}

class TriangleBaseHeight implements Shape {
    private final double base, height;

    public TriangleBaseHeight(double base, double height) {
        if (base <= 0 || height <= 0) throw new IllegalArgumentException("Base and height must be positive");
        this.base = base;
        this.height = height;
    }

    @Override public double area() { return 0.5 * base * height; }

    @Override public double perimeter() {
        throw new UnsupportedOperationException("Perimeter requires all three sides");
    }

    @Override public String name() { return "Triangle (base x height)"; }
}

class TriangleSides implements Shape {
    private final double a, b, c;

    public TriangleSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Sides must be positive");
        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("Triangle inequality violated");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override public double area() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override public double perimeter() { return a + b + c; }
    @Override public String name()      { return "Triangle (three sides)"; }
}