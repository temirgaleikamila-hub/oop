public class Main {
    public static void main(String[] args) {
        Charity charity1 = new Charity(1, "Hope Foundation", "Health", "hope@mail.com");
        Charity charity2 = new Charity(2, "Bright Future", "Education", "bright@mail.com");

        Donor donor1 = new Donor(101010, "Kamila", "k@mail.com", "+77011234567", "Individual");
        Donor donor2 = new Donor(202020, "Aigul", "a@mail.com", "+77021234567", "Corporate");

        Donation donation1 = new Donation(11, donor1, charity1,15000, "2025-12-15", "Card", "paid");
        Donation donation2 = new Donation(12, donor2, charity2, 50000, "2025-12-15", "Kaspi", "paid");

        System.out.println(charity1);
        System.out.println(charity2);
        System.out.println("charity 1 equals charity 2? " + charity1.equals(charity2));

        System.out.println(donor1);
        System.out.println(donor2);
        System.out.println("donor 1 equals donor 2? " + donor1.equals(donor2));
        System.out.println("donation and donor");

        System.out.println(donation1);
        System.out.println(donation2);
        System.out.println("donation 1 equals donation 2? " + donation1.equals(donation2));

    }
}
