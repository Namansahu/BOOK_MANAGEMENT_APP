package Book_management;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BookSerializer {
    private static final String FILE_PATH = "bookdata.ser";

    public static void saveData(Map<Integer, Book> bookDatabase) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(bookDatabase);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
	public static Map<Integer, Book> loadData() {
        Map<Integer, Book> bookDatabase = new HashMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();
            if (obj instanceof Map<?, ?>) {
                bookDatabase = (Map<Integer, Book>) obj;
                System.out.println("Data loaded successfully.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return bookDatabase;
    }
}