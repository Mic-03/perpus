class Item {
    private int id;
    private String type;
    private String title;
    private boolean borrowed;

    public Item(int id, String type, String title) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.borrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}