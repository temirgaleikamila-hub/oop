public class Main {
    public static void main(String[] args) {

        Donor donor1 = new Donor(101010, "Kamila", "k@mail.com", "+77011234567", "Individual");
        Donor donor2 = new Donor(202020, "Aigul", "a@mail.com", "+77021234567", "Corporate");
        Donation donation1 = new Donation(11, donor1, 15000, "2025-12-15", "Card", "paid");
        Donation donation2 = new Donation(12, donor2, 50000, "2025-12-15", "Kaspi", "paid");

        System.out.println(donation1);
        System.out.println(donation2);
        System.out.println("donation 1 equals donation 2? " + donation1.equals(donation2));
        System.out.println(donor1);
        System.out.println(donor2);
        System.out.println("donor 1 equals donor 2? " + donor1.equals(donor2));
    }
}
