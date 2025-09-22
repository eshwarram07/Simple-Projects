package practice;

import java.util.Scanner;

interface Function {
	int add(int a, int b);

	int sub(int a, int b);

	int mul(int a, int b);

	int div(int a, int b);
}

class Calculater implements Function {

	@Override
	public int add(int a, int b) {
		int c = a + b;
		return c;

	}

	@Override
	public int sub(int a, int b) {
		int c = a - b;
		return c;

	}

	@Override
	public int mul(int a, int b) {
		int c = a * b;
		return c;

	}

	@Override
	public int div(int a, int b) {
		int c = a / b;

		return c;

	}

}

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Function calc = new Calculater();
		while (true) {
			System.out.println("\n====== Calculator Menu ======");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. Division");
			System.out.println("5. Exit");
			System.out.println("Enter your choice:- ");
			int choice = sc.nextInt();
			if(choice==5) {
				System.exit(0);
				System.out.println("Exit Successfull");
			}
			else {
			System.out.print("Enter a value:-  ");
			int a = sc.nextInt();
			System.out.print("Enter a value:- ");
			int b = sc.nextInt();
			
			switch (choice) {
			
			case 1:
				System.out.println("Addition value :"+calc.add(a, b));
				break;
			case 2:
				System.out.println("Subraction value :"+ calc.sub(a, b));
				break;
			case 3:
				System.out.println("multiplication value :" +calc.mul(a, b));
				break;
			case 4:
				System.out.println("Division value :" + calc.div(a, b));
				break;
			

		}
			}

	}
	}
}
