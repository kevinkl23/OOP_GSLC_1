import java.util.ArrayList;
import java.util.Scanner;

public class librarySystem {
	
	public static int status = 0;
	public static Scanner scan = new Scanner(System.in);
	
	
	static void logIn(ArrayList<String> registeredStudents, ArrayList<String> password, String studentID) {
		if(registeredStudents.contains(studentID)) {
			System.out.println("Enter Password : ");
			String pass = scan.next();
			scan.nextLine();
			if(password.get(registeredStudents.indexOf(studentID)).equals(pass)) {
				System.out.println("Welcome!");
				System.out.println("======================");
				status = 1;
			}
			else {
				System.out.println("Wrong Password!");
			}
		}
		else {
			int input = 0;
			System.out.println("Student ID is not registered");
			System.out.println("Do you want to register?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.print(">>");
			input = scan.nextInt();
			
			if(input == 1) {
				System.out.print("Enter Student ID : ");
				String ID = scan.next();
				scan.nextLine();
				System.out.println("Enter Password : ");
				String pass = scan.next();
				scan.nextLine();
				registeredStudents.add(ID);
				password.add(pass);
				
				System.out.println("Successfully Registered");
				System.out.println("Please Re-Login");
				System.out.println("======================");
			}
			else {
				System.out.println("Thank You!");
				System.out.println("======================");
			}
		}
	}
	
	static void borrowBook(ArrayList<String> borrowedBooksID, ArrayList<String> borrowedBooks, ArrayList<String> booksList, ArrayList<String> booksID) {
		int input;
		System.out.println("Available Books");
		System.out.println("======================");
		int count = 1;
		for(Object e: booksList) {
			System.out.println(count + ". " + e);
			count++;
			
		}
		System.out.println("Press 0 to back");
		System.out.print(">> ");
		input = scan.nextInt();
		
		if(input == 0) {
			return;
		}
		
		borrowedBooksID.add(booksID.get(input - 1));
		borrowedBooks.add(booksList.get(input-1));
		booksList.remove(input-1);
		booksID.remove(input-1);
		
		System.out.println("Thank You!");
		System.out.println("======================");
		
	}
	
	static void returnBook(ArrayList<String> borrowedBooksID, ArrayList<String> borrowedBooks, ArrayList<String> booksList, ArrayList<String> booksID) {
		String bookID;
		System.out.println("Return Book");
		System.out.println("======================");
		System.out.print("Enter Book ID (Press 0 to back): ");
		bookID = scan.next();
		scan.nextLine();
//		System.out.println(bookID);
		
		if(bookID.equals("0")) {
			return;
		}
		
		if(borrowedBooksID.contains(bookID)) {
			int index = borrowedBooksID.indexOf(bookID);
			booksID.add(bookID);
			booksList.add(borrowedBooks.get(index));
			borrowedBooks.remove(index);
			borrowedBooksID.remove(index);
			
			System.out.println("Book has been returned!");
			System.out.println("Thank You!");
			System.out.println("======================");
		}
		else {
			System.out.println("Invalid Book ID!");
			System.out.println("======================");
		}
	}
	
	static void viewBooks(ArrayList<String> borrowedBooks) {
		int count = 0;
		System.out.println("Borrowed Books");
		System.out.println("======================");
		for(Object e: borrowedBooks) {
			System.out.println(++count + ": " + e);
		}
		if(count == 0) {
			System.out.println("No Borrowed Books");
			System.out.println("======================");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> borrowedBooks = new ArrayList<>();
		ArrayList<String> borrowedBooksID = new ArrayList<>();		
		ArrayList<String> registeredStudents = new ArrayList<>();
		ArrayList<String> password = new ArrayList<>();
		ArrayList<String> booksList = new ArrayList<>();
		ArrayList<String> booksID = new ArrayList<>();
		
		booksList.add("A Tale of Two Cities (BI001)");
		booksList.add("The Lord of the Rings (BI002)");
		booksList.add("The Little Prince(BI003)");
		booksList.add("And Then There Were None (BI004)");
		booksList.add("The Hobbit (BI005)");
		
		booksID.add("BI001");
		booksID.add("BI002");
		booksID.add("BI003");
		booksID.add("BI004");
		booksID.add("BI005");
		
		int input;

		do {
			System.out.println("Bina Nusantara Library");
			System.out.println("======================");
			System.out.println("1. Log In");
			System.out.println("2. Borrow Book");
			System.out.println("3. Return Book");
			System.out.println("4. View Borrowed Book");			
			System.out.println("5. Exit/Log Out");
			System.out.print(">>");
			
			input = scan.nextInt();
			
			if(input > 5 || input < 1) {
				System.out.println("Invalid Input");
				System.out.println("======================");
				continue;
			}
			
			switch(input) {
				case 1:
					if(status == 1) {
						System.out.println("Already Logged In!");
						continue;
					}
					String studentID;
					System.out.println("Log In");
					System.out.println("======================");
					System.out.print("Enter Student ID : ");
					studentID = scan.next();
					scan.nextLine();
					
					logIn(registeredStudents, password, studentID);
					
					break;
				case 2:
					if(status == 0) {
						System.out.println("Please Log In First!");
						System.out.println("======================");
						continue;
					}
					else {
						borrowBook(borrowedBooksID, borrowedBooks, booksList, booksID);
					}
					
					break;
				case 3:
					if(status == 0) {
						System.out.println("Please Log In First!");
						System.out.println("======================");
						continue;
					}
					else {					
						returnBook(borrowedBooksID, borrowedBooks, booksList, booksID);
					}
					break;
				case 4:
					viewBooks(borrowedBooks);
			}
			
		}while(input != 5);
		
		status = 0;
	}

}
