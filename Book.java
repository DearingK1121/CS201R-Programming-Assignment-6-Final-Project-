public class Book extends Media {
    private String author;
    private int pages;

    public Book(int id, String title, String genre, int qty, String author, int pages) {
        super(id, title, genre, qty);
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() { return author; }
    public int getPages() { return pages; }

    @Override
    public String toCSV() {
        return "Book," + getTitle() + "," + getGenre() + "," + getQty() + "," + author + "," + pages + "," + getItemID();
    }

    @Override
    public String toString() {
        return super.toString() + " | Author: " + author + " | Pages: " + pages;
    }
}
