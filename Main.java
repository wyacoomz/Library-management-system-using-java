import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returned() {
        isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : "");
    }
}

class User {
    private String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String toString() {
        return id + " - " + name;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
            }
        }
    }

    public void issueBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                book.issue();
                System.out.println("Book issued: " + title);
                return;
            }
        }
        System.out.println("Book not available.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                book.returned();
                System.out.println("Book returned: " + title);
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        library.addBook(new Book("Java Basics", "James Gosling"));
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Clean Code", "Robert C. Martin"));

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Show Available Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> library.showAvailableBooks();
                case 2 -> {
                    System.out.print("Enter the book title to issue: ");
                    String title = sc.nextLine();
                    library.issueBook(title);
                }
                case 3 -> {
                    System.out.print("Enter the book title to return: ");
                    String title = sc.nextLine();
                    library.returnBook(title);
                }
                case 4 -> {
                    System.out.println("Exiting Library System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
