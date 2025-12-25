import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                if (choice.equals("1")) addDonor();
                else if (choice.equals("2")) addCharity();
                else if (choice.equals("3")) addDonation();
                else if (choice.equals("4")) pool.printAll();
                else if (choice.equals("5")) searchCharity();
                else if (choice.equals("6")) filterDonations();
                else if (choice.equals("7")) sortDonations();
                else if (choice.equals("0")) {
                    System.out.println("Bye!");
                    return;
                } else {
                    System.out.println("Unknown choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Donation System ===");
        System.out.println("1) Add donor");
        System.out.println("2) Add charity");
        System.out.println("3) Add donation");
        System.out.println("4) List all");
        System.out.println("5) Search charity");
        System.out.println("6) Filter donations");
        System.out.println("7) Sort donations");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }
}

