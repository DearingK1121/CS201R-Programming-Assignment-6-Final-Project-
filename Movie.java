public class Movie extends Media {
    private String director;
    private int runtime; // in minutes

    public Movie(int id, String title, String genre, int qty, String director, int runtime) {
        super(id, title, genre, qty);
        this.director = director;
        this.runtime = runtime;
    }

    public String getDirector() { return director; }
    public int getRuntime() { return runtime; }

    @Override
    public String toCSV() {
        return "Movie," + getTitle() + "," + getGenre() + "," + getQty() + "," + director + "," + runtime + "," + getItemID();
    }

    @Override
    public String toString() {
        return super.toString() + " | Director: " + director + " | Runtime: " + runtime + " mins";
    }
}
