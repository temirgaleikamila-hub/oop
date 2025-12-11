public class Main{
    public static void main(String[] args){
    Donor donor1 = new Donor("Kamila");
    Donor donor2 = new Donor("Danial");
    Donor donor3 = new Donor("Yasmina");

    Donation donation1 = new Donation(donor1, 5000, "Pet Support");
    Donation donation2 = new Donation(donor2, 15000, "Food Support");
    Donation donation3 = new Donation(donor3, 10000, "Medical Support");

    Charity charity1 = new Charity("Helping pets", "Helping families in need", "Helping ill patients");
    System.out.println("Donor:");
    System.out.println(donor1);
    System.out.println(donor2);
    System.out.println(donor3);

    System.out.println("Donation:");
    System.out.println(donation1);
    System.out.println(donation2);
    System.out.println(donation3);

    System.out.println("Charity organization:");
    System.out.println(charity1);


        //create donor, donation and charity objects

    }
}
