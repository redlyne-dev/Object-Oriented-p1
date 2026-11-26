import java.util.Scanner;

/**
 * Procedural (non-OOP) area calculator.
 *
 * Data and behaviour are kept separate:
 *   - the numbers live in local variables inside main()
 *   - the math lives in static helper methods
 *
 * There are no objects, no inheritance, no polymorphism.
 */
public class Pareac {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(in, "Choose a shape: ");

            switch (choice) {
                case 1: {
                    double r = readDouble(in, "Radius: ");
                    System.out.printf("Circle    area = %.4f   circumference = %.4f%n",
                            circleArea(r), circleCircumference(r));
                    break;
                }
                case 2: {
                    double w = readDouble(in, "Width:  ");
                    double h = readDouble(in, "Height: ");
                    System.out.printf("Rectangle area = %.4f   perimeter = %.4f%n",
                            rectangleArea(w, h), rectanglePerimeter(w, h));
                    break;
                }
                case 3: {
                    double b = readDouble(in, "Base:   ");
                    double h = readDouble(in, "Height: ");
                    System.out.printf("Triangle  area = %.4f%n", triangleArea(b, h));
                    break;
                }
                case 4: {
                    double a = readDouble(in, "Side a: ");
                    double b = readDouble(in, "Side b: ");
                    double c = readDouble(in, "Side c: ");
                    if (!isValidTriangle(a, b, c)) {
                        System.out.println("Those sides do not form a triangle.");
                    } else {
                        System.out.printf("Triangle  area = %.4f   perimeter = %.4f%n",
                                triangleAreaSides(a, b, c), a + b + c);
                    }
                    break;
                }
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Please choose 0-4.");
            }
            System.out.println();
        }

        in.close();
        System.out.println("Bye.");
    }

    // ---------------------------------------------------------------- shapes

    /** Area of a circle: PI * r^2 */
    public static double circleArea(double radius) {
        return Math.PI * radius * radius;
    }

    /** Circumference of a circle: 2 * PI * r */
    public static double circleCircumference(double radius) {
        return 2 * Math.PI * radius;
    }

    /** Area of a rectangle: w * h */
    public static double rectangleArea(double width, double height) {
        return width * height;
    }

    /** Perimeter of a rectangle: 2 * (w + h) */
    public static double rectanglePerimeter(double width, double height) {
        return 2 * (width + height);
    }

    /** Area of a triangle from base and height: (b * h) / 2 */
    public static double triangleArea(double base, double height) {
        return 0.5 * base * height;
    }

    /** Area of a triangle from its three sides (Heron's formula). */
    public static double triangleAreaSides(double a, double b, double c) {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /** Triangle inequality check. */
    public static boolean isValidTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0
                && a + b > c
                && a + c > b
                && b + c > a;
    }

    // ---------------------------------------------------------------- ui

    private static void printMenu() {
        System.out.println("--- Area calculator (procedural) ---");
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