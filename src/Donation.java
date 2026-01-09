public class Donation {

    private int donationId; //hidden data (access only with methods)
    private Donor donor;
    private Charity charity;
    private double amount;
    private String donatedAt;
    private String paymentMethod;
    private String status;


    public Donation(int donationId, Donor donor, Charity charity,
                    double amount, String donatedAt,
                    String paymentMethod, String status) {
        this.donationId = donationId;
        this.donor = donor;
        this.charity=charity;
        this.amount = amount;
        this.donatedAt = donatedAt;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String toString() {
        return "Donation{id=" + donationId +
                ", donor=" + donor.getFullName() +
                ", charity=" + charity.getName() +
                ", amount=" + amount + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donation)) return false;
        Donation d = (Donation) o;
        return donationId == d.donationId;
    }

    @Override
    public int hashCode() {
        return donationId;
    }
}



