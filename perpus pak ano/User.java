class User {
    private int id;
    private String type;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public User(int id, String type, String name, String address, String phoneNumber, String email) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void borrowItem(Item item) {
        System.out.println(name + " has borrowed: " + item.getType() + " - " + item.getTitle());
    }
}