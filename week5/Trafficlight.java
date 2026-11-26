import java.util.Scanner;

public class Trafficlight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Added Scanner initialization
        boolean isRushtime = false;

        // rush hour boolean
        System.out.print("is it rush time: ");
        String answer = scanner.next().trim().toLowerCase();

        if (answer.equals("yes") || answer.equals("y")) { // Fixed typo: eyeals -> equals
            isRushtime = true;
        } else if (answer.equals("no") || answer.equals("n")) { // Fixed: ("n") -> answer.equals("n")
            isRushtime = false;
        } else {
            System.out.println("invalid answer");
        }

        // time simulation
        System.out.print("Enter simulation duration: ");
        int totalTime = scanner.nextInt();
        if (totalTime <= 0) {
            System.out.println("time must be positive ");
            scanner.close();
            return; // Added return to exit early on invalid input
        }

        // light durations
        final int GREEN = isRushtime ? 45 : 30;
        final int AMBER = 5;
        final int RED = 25;
        final int CYCLE = GREEN + RED + AMBER;

        System.out.println("\n RUSH HOUR :" + (isRushtime ? "YES" : "NO"));
        System.out.println("Cycle Length :" + CYCLE + " seconds");
        System.out.println("================");

        // simulate second by second
        int second = 1;
        while (second <= totalTime) {
            int position = (second - 1) % CYCLE;

            String color; // Fixed: string color: -> String color;
            int remaining;
            if (position < GREEN) {
                color = "GREEN";
                remaining = GREEN - position;
            } else if (position < GREEN + AMBER) {
                color = "AMBER";
                remaining = (GREEN + AMBER) - position; // Fixed calculation
            } else {
                color = "RED";
                remaining = CYCLE - position;
            }

            // Moved output & increment outside the if/else block
            System.out.printf("Second %3d: %-6s - %2d second(s) remaining%n", second, color, remaining);
            second++;
        }

        // Moved cycle summary and scanner close outside the while loop
        int completeCycles = totalTime / CYCLE;
        System.out.println("completed cycles :" + completeCycles);
        scanner.close();
    }
}