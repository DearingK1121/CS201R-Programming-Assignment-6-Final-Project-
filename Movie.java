public class Movie extends Media {
    private String director;
    private int runtimeMinutes;

    public Movie(int itemID, String title, String genre, int quantity,
                 String director, int runtimeMinutes) {
        super(itemID, title, genre, quantity);
        this.director = director;
        this.runtimeMinutes = runtimeMinutes;
    }

    @Override
    public String getType() { return "Movie"; }

    @Override
    public String getDetails() {
        return "Director: " + director + ", Runtime: " + runtimeMinutes + " min";
    }

    @Override
    public String toCSV() {
        return "Movie," + title + "," + genre + "," + quantity + "," +
                director + "," + runtimeMinutes + "," + itemID;
    }
}
