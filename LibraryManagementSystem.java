package Simpleprj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Library {
	private List<Book> books;
	private List<User> users;

	public Library() {
		books = new ArrayList<>();
		users = new ArrayList<>();
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void addUser(User user) {
		users.add(user);
	}

	public void showBooks() {
		for (Book b : books) {
			System.out.println(b);
		}
	}

	public void showUsers() {
		for (User u : users) {
			System.out.println(u);
		}
	}

	public void issueBook(int bookId, int userId) {
		Book book = findBookById(bookId);
		User user = findUserById(userId);

		if (book != null && user != null && !book.isIssued()) {
			book.issueBook();
			user.borrowBook(book);
			System.out.println("Book issued successfully!");
		} else {
			System.out.println("Book not available or invalid user.");
		}
	}

	public void returnBook(int bookId, int userId) {
		Book book = findBookById(bookId);
		User user = findUserById(userId);

		if (book != null && user != null && book.isIssued()) {
			book.returnBook();
			user.returnBook(book);
			System.out.println("Book returned successfully!");
		} else {
			System.out.println("Invalid return operation.");
		}
	}

	private Book findBookById(int id) {
		for (Book b : books) {
			if (b.getId() == id)
				return b;
		}
		return null;
	}

	private User findUserById(int id) {
		for (User u : users) {
			if (u.getId() == id)
				return u;
		}
		return null;
	}
}

class Book {
	private int id;
	private String title;
	private String author;
	private boolean isIssued;

	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isIssued = false; // by default available
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean isIssued() {
		return isIssued;
	}

	public void issueBook() {
		isIssued = true;
	}

	public void returnBook() {
		isIssued = false;
	}

	@Override
	public String toString() {
		return id + " - " + title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
	}

}

class User {
	private int id;
	private String name;
	private List<Book> borrowedBooks;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
		this.borrowedBooks = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}

	public void borrowBook(Book book) {
		borrowedBooks.add(book);
	}

	public void returnBook(Book book) {
		borrowedBooks.remove(book);
	}

	@Override
	public String toString() {
		return id + " - " + name + " (Borrowed: " + borrowedBooks.size() + " books)";
	}
}

public class Library_Management_System {

	public static void main(String[] args) {
		Library library = new Library();

		
		library.addBook(new Book(1, "Java Programming", "James Gosling"));
		library.addBook(new Book(2, "Python Basics", "Guido van Rossum"));
		library.addBook(new Book(3, "C++ Guide", "Bjarne Stroustrup"));

		
		library.addUser(new User(101, "Eshwar"));
		library.addUser(new User(102, "Ram"));

		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n==== Library Menu ====");
			System.out.println("1. Show Books");
			System.out.println("2. Show Users");
			System.out.println("3. Issue Book");
			System.out.println("4. Return Book");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				library.showBooks();
				break;
			case 2:
				library.showUsers();
				break;
			case 3:
				System.out.print("Enter Book ID: ");
				int bookId = sc.nextInt();
				System.out.print("Enter User ID: ");
				int userId = sc.nextInt();
				library.issueBook(bookId, userId);
				break;
			case 4:
				System.out.print("Enter Book ID: ");
				int returnBookId = sc.nextInt();
				System.out.print("Enter User ID: ");
				int returnUserId = sc.nextInt();
				library.returnBook(returnBookId, returnUserId);
				break;
			case 5:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid choice!");
			}
		} while (choice != 5);

		sc.close();
	}

}
