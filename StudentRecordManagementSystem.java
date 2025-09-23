package Simpleprj;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

class Student {
	int id;
	String name;
	double marks;

	Student(int id, String name, double marks) {
		this.id = id;
		this.name = name;
		this.marks = marks;

	}

	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
	}

}

public class StudentDetails {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Student> detail = new ArrayList<Student>();

		while (true) {
			System.out.println("====== Students Menu ======");
			System.out.println("1. Add Student");
			System.out.println("2. Delete Student");
			System.out.println("3. Update Studdnt");
			System.out.println("4. View a particular Student");
			System.out.println("5. View all particular Students");
			System.out.println("6. Exit");
			System.out.println("Enter your choice:- ");
			int choice = sc.nextInt();
			if (choice == 6) {
				System.out.println("Exit Successfull");
				System.exit(0);

			}

			else {
				switch (choice) {

				case 1:
					System.out.println("Enter the student id :");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter the student name:");
					String name = sc.nextLine();

					System.out.println("Enter the student Marks:");
					double marks = sc.nextDouble();

					detail.add(new Student(id, name, marks));
					System.out.println("Student Added");
					break;

				case 2:
					System.out.println("Enter the Dtails to remove");
					int ids = sc.nextInt();
					boolean removed = detail.removeIf(s -> s.id == ids);
					if (removed)
						System.out.println("Deleted Successfully");
					else
						System.out.println("Id is not valid");

					break;

				case 3:
					System.out.println("====== Students edited menu Menu ======");
					System.out.println("1. Edit Student name");
					System.out.println("2. Edit Student marks");
					System.out.println("Enter the choice What you what to edit:");
					int choices = sc.nextInt();
					System.out.println("Enter the updation id");
					int upid = sc.nextInt();
					sc.nextLine();
					

					switch (choices) {
					case 1:
						boolean found = false;
						for (Student s : detail) {
							if (s.id == upid) {
								System.out.print("Enter new name: ");
								s.name = sc.nextLine();
								found = true;
							}
							if (!found)
								System.out.println("Student id not found");
							else
								System.out.println("Updated Successfull");

						}
						break;
					case 2:
						boolean founded = false;
						for (Student s : detail) {
							if (s.id == upid) {
								System.out.print("Enter new marks: ");
								s.marks = sc.nextDouble();
								found = true;
							}
							if (!founded)
								System.out.println("Student id not found");
							else
								System.out.println("Updated Successfull");

						}
						break;
					}

				case 4:
					System.out.println("Enter a specific student id:");
					int spId = sc.nextInt();
					boolean founds = false;
					for (Student s : detail) {
						if (s.id == spId) {
							System.out.println(s);
						}
						founds = true;

					}

					if (!founds)
						System.out.println("student id found");
					else
						System.out.println("Student id not found ");

					break;

				case 5:
					if (detail.isEmpty()) {
						System.out.println("no student are available");
					}
					for (Student s : detail) {
						System.out.println(s);

					}
					break;

				}
			}

		}

	}

}
