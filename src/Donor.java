public class Donor {
//данные скрыты внутри класса
    private int donorId;
    private String fullName;
    private String email;
    private String phone;
    private String donorType;


    //конструктор класса донор
    public Donor(int donorId, String fullName, String email, String phone, String donorType) {
        this.donorId = donorId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.donorType = donorType;
    }
    //ссылка на текущий контекст объекта который мы создаем

    //using getter and setter
    public int getDonorId() {
        return donorId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDonorType() {
        return donorType;
    }

  public void setDonorType(String donorType){
        this.donorType=donorType;
  }
    public boolean equals(Donor other){
        return other != null && this.donorId==other.donorId;
    }

    @Override
    public String toString() {
        return "Donor{id=" + donorId + ", name='" + fullName + "', type='" + donorType + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donor)) return false;
        Donor d = (Donor) o;
        return donorId == d.donorId;
    }

    @Override
    public int hashCode() {
        return donorId;
    }
}