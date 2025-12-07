public abstract class Media {
    private int itemID;
    private String title;
    private String genre;
    private int qty;

    public Media(int itemID, String title, String genre, int qty) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.qty = qty;
    }

    public int getItemID() { return itemID; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getQty() { return qty; }

    public void setTitle(String title) { this.title = title; }

    public boolean borrowItem() {
        if (qty > 0) {
            qty--;
            return true;
        }
        return false;
    }

    public void returnItem() {
        qty++;
    }

    // CSV export
    public abstract String toCSV();

    @Override
    public String toString() {
        return itemID + ": " + title + " | Genre: " + genre + " | Qty: " + qty;
    }
}
