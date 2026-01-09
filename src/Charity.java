public class Charity {
    
    private int charityId;
    private String name;
    private String category;
    private String email;


    public Charity(int charityId, String name, String category, String email) {
        this.charityId = charityId;
        this.name = name;
        this.category = category;
        this.email = email;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }
    public int getCharityId() {
        return charityId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Charity{id=" + charityId + ", name='" + name + "'}";
    }

    @Override //subclass redefines a method from its parent class to change the behavior
    public int hashCode() {
        return charityId;
    } //hash of object is its id, represents object in hashed form
    //used to quickly find, compare, or store objects

    public boolean equals(Charity other) {
        return other != null && this.charityId == other.charityId;
    }
}

