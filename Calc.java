import java.util.Scanner;

public class Calc{
    public static void main(string[] args) {
    Scanner scanner =new Scanner(System.in);

    System.out.print("SIMPLE INTEREST CALCULATOR");

    System.out.print("enter Principle amount ");
    double principle =scanner.nextDouble();

    System.out.print("Enter rate pa.");
    double rate =scanner.nextDouble();

    System.out.print("Enter the time in years");
    double time=scanner.nextDouble();

    double simpleInterest=(principle * rate* time)/100;
    double totalAmount = principle + simpleInterest ;

    System.out.printf("Interest gained: $%,.2f%n ", simpleInterest)
    System.out.printf("total amount: $%,.2f%n", totalAmount)

    scanner.close();
    }
}
