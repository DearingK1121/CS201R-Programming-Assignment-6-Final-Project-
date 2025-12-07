import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class LibraryManager {
    private ArrayList<Media> inventory;
    private Scanner sc;
    private int nextID;

    public LibraryManager() {
        inventory = new ArrayList<>();
        sc = new Scanner(System.in);
        nextID = 1;
    }

    public static void main(String[] args) {
        LibraryManager app = new LibraryManager();
        System.out.println("Community Library — Lending Helper System");
        app.menu();
    }

    // Main Menu Loop
    private void menu() {
        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1 - Add Item");
            System.out.println("2 - Delete Item");
            System.out.println("3 - Modify Item");
            System.out.println("4 - Display Items");
            System.out.println("5 - Save CSV");
            System.out.println("6 - Load CSV");
            System.out.println("7 - Borrow Item");
            System.out.println("8 - Return Item");
            System.out.println("9 - Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1": addItem(); break;
                case "2": deleteItem(); break;
                case "3": modifyItem(); break;
                case "4": displayAll(); break;
                case "5": saveCSV(); break;
                case "6": loadCSV(); break;
                case "7": borrowItem(); break;
                case "8": returnItem(); break;
                case "9":
                    System.out.println("Saving and exiting...");
                    try { saveToCSV("library_inventory.csv"); } catch (Exception e) {}
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // Borrow system
    private void borrowItem() {
        System.out.print("Enter ID to borrow: ");
        int id = Integer.parseInt(sc.nextLine());
        Media m = findByID(id);

        if (m == null) {
            System.out.println("Item not found.");
            return;
        }

        if (m.borrowItem()) {
            logBorrow(m.getItemID(), m.getTitle(), "BORROW");
            System.out.println("Borrowed: " + m.getTitle());
        } else {
            System.out.println("Not enough copies available.");
        }
    }

    // Return system
    private void returnItem() {
        System.out.print("Enter ID to return: ");
        int id = Integer.parseInt(sc.nextLine());
        Media m = findByID(id);

        if (m == null) {
            System.out.println("Item not found.");
            return;
        }

        m.returnItem();
        logBorrow(m.getItemID(), m.getTitle(), "RETURN");
        System.out.println("Returned: " + m.getTitle());
    }

    /* ---------------- Write to the .txt log file ---------------- */
    private void logBorrow(int id, String title, String action) {
        try (FileWriter fw = new FileWriter("borrow_log.txt", true)) {
            fw.write(id + " | " + title + " | " + action + " | " + LocalDate.now() + "\n");
        } catch (Exception e) {
            System.out.println("Could not write to log file.");
        }
    }

    /* ---------------- Add, Modify, Delete, Display ---------------- */

    private void addItem() {
        System.out.println("1 = Book, 2 = Movie");
        String c = sc.nextLine();

        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        System.out.print("Quantity: ");
        int qty = Integer.parseInt(sc.nextLine());

        int id = nextID++;

        if (qty < 0) qty = 0;

        if (c.equals("1")) {
            System.out.print("Author: ");
            String author = sc.nextLine();
            System.out.print("Pages: ");
            int pages = Integer.parseInt(sc.nextLine());
            inventory.add(new Book(id, title, genre, qty, author, pages));
        } else {
            System.out.print("Director: ");
            String director = sc.nextLine();
            System.out.print("Runtime (mins): ");
            int runtime = Integer.parseInt(sc.nextLine());
            inventory.add(new Movie(id, title, genre, qty, director, runtime));
        }

        System.out.println("Item added.");
    }

    private void deleteItem() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        Media m = findByID(id);

        if (m != null) {
            inventory.remove(m);
            System.out.println("Deleted.");
        } else {
            System.out.println("Not found.");
        }
    }

    private void modifyItem() {
        System.out.print("Enter ID to modify: ");
        int id = Integer.parseInt(sc.nextLine());
        Media m = findByID(id);

        if (m == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("New title: ");
        m.setTitle(sc.nextLine());
        System.out.println("Updated.");
    }

    private void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }

        for (Media m : inventory) System.out.println(m);
    }

    /* ---------------- CSV load/save ---------------- */

    private void saveCSV() {
        System.out.print("Filename: ");
        String file = sc.nextLine();
        try {
            saveToCSV(file);
            System.out.println("Saved.");
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    private void loadCSV() {
        System.out.print("Filename: ");
        String file = sc.nextLine();
        try {
            loadFromCSV(file);
            System.out.println("Loaded.");
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    public void saveToCSV(String filename) throws Exception {
        PrintWriter pw = new PrintWriter(new FileWriter(filename));
        for (Media m : inventory)
            pw.println(m.toCSV());
        pw.close();
    }

    public void loadFromCSV(String filename) throws Exception {
        Scanner file = new Scanner(new File(filename));
        inventory.clear();

        while (file.hasNextLine()) {
            String[] t = file.nextLine().split(",");
            if (t[0].equals("Book")) {
                inventory.add(new Book(
                        Integer.parseInt(t[6]), t[1], t[2],
                        Integer.parseInt(t[3]), t[4], Integer.parseInt(t[5])));
            } else {
                inventory.add(new Movie(
                        Integer.parseInt(t[6]), t[1], t[2],
                        Integer.parseInt(t[3]), t[4], Integer.parseInt(t[5])));
            }
        }
        file.close();
    }

    /* ---------------- Helper ---------------- */
    private Media findByID(int id) {
        for (Media m : inventory)
            if (m.getItemID() == id)
                return m;
        return null;
    }
}
