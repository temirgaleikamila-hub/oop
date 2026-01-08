import java.util.Scanner;
import java.util.ArrayList; //resizable array

public class Main {
    private static final Scanner sc = new Scanner(System.in);  //inputting values
    private static final ArrayList<Donor> donors = new ArrayList<>();
    private static final ArrayList<Charity> charities = new ArrayList<>();
    private static final ArrayList<Donation> donations = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                if (choice.equals("1")) addDonor();
                else if (choice.equals("2")) searchCharity();
                else if (choice.equals("3")) filterDonations();
                else if (choice.equals("4")) sortDonations();
                else {
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
        System.out.println("2) Search charity");
        System.out.println("3) Filter donations");
        System.out.println("4) Sort donations");
        System.out.print("Choose: ");
    }

    private static void addDonor() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Full name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Donor type (Individual/Corporate): ");
        String type = sc.nextLine();

        Donor d = new Donor(id, name, email, phone, type);
        donors.add(d);
        System.out.println("Donor added.");
    }

    private static void searchCharity() {
        System.out.print("Enter charity name: ");
        String name = sc.nextLine().trim();
        boolean found = false;
        for (Charity c : charities) {
            if (c.getName().equalsIgnoreCase(name)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("Not found.");
    }

    private static void filterDonations() {
        System.out.print("Min amount: ");
        String input = sc.nextLine();
        boolean found = false;
        for (Donation d : donations) {
            if (String.valueOf(d.getAmount()).compareTo(input) >= 0) {
                System.out.println(d);
                found = true;
            }
        }
        if (!found) System.out.println("No donations found.");
    }

    private static void sortDonations() {
        for (int i = 0; i < donations.size() - 1; i++) {
            for (int j = i + 1; j < donations.size(); j++) {
                if (donations.get(i).getAmount() > donations.get(j).getAmount()) {
                    Donation tmp = donations.get(i);
                    donations.set(i, donations.get(j));
                    donations.set(j, tmp);
                }
            }
        }
        System.out.println("Sorted by amount.");
    }
}

