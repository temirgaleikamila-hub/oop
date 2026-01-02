import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {

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

    private static void addDonor() {
        System.out.print("Donor type (1=Individual, 2=Corporate): ");
        String t = sc.nextLine().trim();

        int id = readInt("ID (int): ");
        System.out.print("Full name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
    }
}

