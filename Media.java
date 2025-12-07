import java.io.*;

public abstract class Media {
    protected int itemID;
    protected String title;
    protected String genre;
    protected int quantity;

    public Media(int itemID, String title, String genre, int quantity) {
        this.itemID = itemID;
        this.title = title;
        this.genre = genre;
        this.quantity = quantity;
    }

    public int getItemID() { return itemID; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getQuantity() { return quantity; }

    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean borrowItem() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }

    public void returnItem() {
        quantity++;
    }

    public abstract String getType();
    public abstract String getDetails();
    public abstract String toCSV();

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | qty: %d | %s",
            itemID, getType(), title, quantity, getDetails());
    }
}
