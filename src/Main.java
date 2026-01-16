import org.postgresql.Db;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Donation> donations = new ArrayList<>();
    private static final ArrayList<Donor> donors = new ArrayList<>();
    private static final ArrayList<Charity> charities = new ArrayList<>();

    public static void main(String[] args) {
        Connection conn = Db.getConnection();
        System.out.println("Database connected!");
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            try {
                if (choice.equals("1")) addDonor();
                else if (choice.equals("2")) searchCharity();
                else if (choice.equals("3")) filterDonations();
                else if (choice.equals("4")) sortDonations();
                else if (choice.equals("5")) createDonor();
                else if (choice.equals("6")) readAllDonors();
                else if (choice.equals("7")) updateDonor();
                else if (choice.equals("8")) deleteDonor();
                else if (choice.equals("0")) {
                    System.out.println("Bye!");
                    break;
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
        System.out.println("2) Search charity");
        System.out.println("3) Filter donations");
        System.out.println("4) Sort donations");
        System.out.println("5) Create donor in DB");
        System.out.println("6) Show all donors from DB");
        System.out.println("7) Update donor in DB");
        System.out.println("8) Delete donor from DB");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private static void addDonor() {
        System.out.print("ID: ");
        int donorId = Integer.parseInt(sc.nextLine());
        System.out.print("Full name: ");
        String fullName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Donor type (Individual/Corporate): ");
        String donorType = sc.nextLine();
        Donor d = new Donor(donorId, fullName, email, phone, donorType);
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
        double minAmount = Double.parseDouble(sc.nextLine());

        boolean found = false;
        for (Donation d : donations) {
            if (d.getAmount() >= minAmount) {
                System.out.println(d);
                found = true;
            }
        }

        if (!found) System.out.println("No donations found.");
    }

    private static void sortDonations() {
        donations.sort((a, b) -> Double.compare(a.getAmount(), b.getAmount()));
        System.out.println("Sorted by amount.");
    }

    private static void createDonor() {
        System.out.print("ID: ");
        int donorId = Integer.parseInt(sc.nextLine());
        System.out.print("Full name: ");
        String fullName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Donor type (Individual/Corporate): ");
        String donorType = sc.nextLine();

        String sql = "INSERT INTO \"Donor\" (donor_id, full_name, email, phone, donor_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, donorId);
            ps.setString(2, fullName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, donorType);
            ps.executeUpdate();
            System.out.println("Donor saved to DB.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }


    private static void readAllDonors() {
        String sql = "SELECT * FROM \"Donor\"";
        try (Connection conn = Db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("donor_id") +
                                ", Name: " + rs.getString("full_name") +
                                ", Email: " + rs.getString("email") +
                                ", Phone: " + rs.getString("phone") +
                                ", Type: " + rs.getString("donor_type")
                );
            }

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    private static void updateDonor() {
        System.out.print("Enter donor ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("New name: ");
        String name = sc.nextLine();
        System.out.print("New email: ");
        String email = sc.nextLine();
        System.out.print("New phone: ");
        String phone = sc.nextLine();
        System.out.print("New type: ");
        String type = sc.nextLine();

        String sql = "UPDATE \"Donor\" SET full_name=?, email=?, phone=?, donor_type=? WHERE donor_id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, type);
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Donor updated in DB.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }

    private static void deleteDonor() {
        System.out.print("Enter donor ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM \"Donor\" WHERE donor_id=?";
        try (Connection conn = Db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Donor deleted from DB.");

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
        }
    }
}
