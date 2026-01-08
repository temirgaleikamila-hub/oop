public class IndividualDonor extends Donor {
    public IndividualDonor(int donorId, String fullName, String email, String phone, String donorType) {
        super(donorId, fullName, email, phone, donorType);
    }

    @Override
    public String getDonorType() {
        return "Individual";
    }
}
