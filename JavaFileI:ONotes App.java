import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

   
    public static void addNote() {
        try (Scanner sc = new Scanner(System.in);
             FileWriter fw = new FileWriter(FILE_NAME, true)) {

            System.out.println("Enter your note:");
            String note = sc.nextLine();

            fw.write(note + System.lineSeparator());
            System.out.println("Note saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while writing note: " + e.getMessage());
        }
    }

    // Method to view notes
    public static void viewNotes() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nYour Notes:");
            System.out.println("--------------------");

            boolean empty = true;
            while ((line = br.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }

            if (empty) {
                System.out.println("No notes found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("⚠️ No notes file found. Add a note first!");
        } catch (IOException e) {
            System.out.println("⚠️ Error while reading notes: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Notes Manager ===");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> addNote();
                case 2 -> viewNotes();
                case 3 -> System.out.println(" Exiting... Goodbye!");
                default -> System.out.println("Invalid choice, try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}
