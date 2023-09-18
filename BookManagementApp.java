package Book_management;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookManagementApp {
    private static Map<Integer, Book> bookDatabase = new HashMap<>();
    private static int nextBookId = 1;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load existing data from file if available
        //bookDatabase = BookSerializer.loadData();

        while (true) {
        	System.out.println("\n*******************************************************:");
        	System.out.println("\n.................BOOK MANAGEMENT APP....................");
        	System.out.println("\n*******************************************************:");
            System.out.println("\nMenu:");
            System.out.println("1. Add a new book Details");
            System.out.println("2. Retrieve book Details by ID b");
            System.out.println("3. Update book details ");
            System.out.println("4. Delete a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    retrieveBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    // Save data to file before exiting
                    BookSerializer.saveData(bookDatabase);
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        try {
            System.out.print("Enter Book Name: ");
            String bookName = scanner.nextLine();

            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();

            // ISBN validation (dummy validation)
            if (!isValidIsbn(isbn)) {
                throw new IllegalArgumentException("Invalid ISBN format.");
            }

            Book newBook = new Book(bookName, nextBookId, isbn);
            bookDatabase.put(nextBookId, newBook);

            System.out.println("Book added successfully with ID: " + nextBookId);
            nextBookId++;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static boolean isValidIsbn(String isbn) {
        // Implement ISBN validation logic here (e.g., length check, format check)
        // For simplicity, we assume any non-empty ISBN is valid in this example.
        return !isbn.isEmpty();
    }

    private static void retrieveBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        Book book = bookDatabase.get(bookId);

        if (book != null) {
            System.out.println("Book Details:\n" + book);
        } else {
            System.err.println("Book not found.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter Book ID to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book book = bookDatabase.get(bookId);

        if (book != null) {
            try {
                System.out.print("Enter new Book Name: ");
                String newBookName = scanner.nextLine();

                System.out.print("Enter new ISBN: ");
                String newIsbn = scanner.nextLine();

                // ISBN validation (dummy validation)
                if (!isValidIsbn(newIsbn)) {
                    throw new IllegalArgumentException("Invalid ISBN format.");
                }

                book = new Book(newBookName, bookId, newIsbn);
                bookDatabase.put(bookId, book);

                System.out.println("Book updated successfully.");
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("Book not found.");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int bookId = scanner.nextInt();

        Book removedBook = bookDatabase.remove(bookId);

        if (removedBook != null) {
            System.out.println("Book deleted successfully.");
        } else {
            System.err.println("Book not found.");
        }
    }
}