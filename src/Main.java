import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Donor> donors = new ArrayList<>();
    private static final ArrayList<Charity> charities = new ArrayList<>();
    private static final ArrayList<Donation> donations = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            try {
                if (choice.equals("1")) addDonor();
                else if (choice.equals("2")) addCharity();
                else if (choice.equals("3")) addDonation();
                else if (choice.equals("4")) printAll();
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

    private static void addDonor() {
        System.out.print("ID (int): ");
        int id = readInt();

        System.out.print("Full name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Donor type (Individual/Corporate): ");
        String type = sc.nextLine();

        donors.add(new Donor(id, name, email, phone, type));
        System.out.println("Donor added.");
    }
    private static void addCharity() {
        System.out.print("ID (int): ");
        int id = readInt();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Category: ");
        String category = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        charities.add(new Charity(id, name, category, email));
        System.out.println("Charity added.");
    }
    private static void addDonation() {
        System.out.print("Donation ID (int): ");
        int donationId = readInt();

        System.out.print("Donor ID (int): ");
        int donorId = readInt();

        System.out.print("Charity ID (int): ");
        int charityId = readInt();

        Donor donor = findDonorById(donorId);
        Charity charity = findCharityById(charityId);

        if (donor == null) {
            System.out.println("Donor not found.");
            return;
        }
        if (charity == null) {
            System.out.println("Charity not found.");
            return;
        }
        System.out.print("Amount: ");
        double amount = readDouble();

        System.out.print("Date (e.g., 2026-01-02): ");
        String date = sc.nextLine();

        System.out.print("Payment method: ");
        String method = sc.nextLine();

        System.out.print("Status: ");
        String status = sc.nextLine();

        donations.add(new Donation(donationId, donor, charity, amount, date, method, status));
        System.out.println("Donation added.");
    }
    private static void printAll() {
        System.out.println("--- Donors ---");
        for (Donor d : donors) System.out.println(d);

        System.out.println("--- Charities ---");
        for (Charity c : charities) System.out.println(c);

        System.out.println("--- Donations ---");
        for (Donation dn : donations) System.out.println(dn);
    }
    //search charity by name
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

    // фильтрация пожертвований по минимальной сумме
    private static void filterDonations() {
        System.out.print("Min amount: ");
        double min = readDouble();

        boolean found = false;
        for (Donation d : donations) {
            if (d.getAmount() >= min) {
                System.out.println(d);
                found = true;
            }
        }
        if (!found) System.out.println("No donations found.");
    }
    // сортировка пожертвований по сумме
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

    //  поиск донора по ID
    private static Donor findDonorById(int id) {
        for (Donor d : donors) {
            if (d.getDonorId() == id) return d;
        }
        return null;
    }

    // поиск charity по ID
    private static Charity findCharityById(int id) {
        for (Charity c : charities) {
            if (c.getCharityId() == id) return c;
        }
        return null;
    }
    //  ввод int с защитой от пустой строки
    private static int readInt() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Enter a valid int: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}

