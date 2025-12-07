public class Book extends Media {
    private String author;
    private int pages;

    public Book(int itemID, String title, String genre, int quantity, String author, int pages) {
        super(itemID, title, genre, quantity);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public String getType() { return "Book"; }

    @Override
    public String getDetails() {
        return "Author: " + author + ", Pages: " + pages;
    }

    @Override
    public String toCSV() {
        return "Book," + title + "," + genre + "," + quantity + "," +
                author + "," + pages + "," + itemID;
    }
}
