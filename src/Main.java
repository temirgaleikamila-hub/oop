public class Main {
    public static void main(String[] args) {

        Donor d1 = new Donor(101010, "Kamila", "k@mail.com", "+7701...", "Individual");
        Donor d2 = new Donor(202020, "Aigul", "a@mail.com", "+7702...", "Corporate");

        System.out.println(d1);
        System.out.println(d2);
        System.out.println("d1 equals d2? " + d1.equals(d2));
    }
}
