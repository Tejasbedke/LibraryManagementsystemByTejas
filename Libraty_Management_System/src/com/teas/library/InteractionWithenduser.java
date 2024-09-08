package com.teas.library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InteractionWithenduser {

	public static void main(String[] args) {

		Scanner scn=new Scanner(System.in);
		LibraryManagementSystem library=new LibraryManagementSystem();
		System.out.println("\n\n\n\n\t\t\t*********WelCome Te@s Library*********");
		int choice=0;
		
		do {
		System.out.println("1::Create Account ");
		System.out.println("2::Login as User");	
		System.out.println("3::Add new Book");
		System.out.println("4::Delete Book");
		System.out.println("5::Exit");
		System.out.println("Enter your Choice");
		choice=scn.nextInt();
		
	
		switch(choice) {
		    //Case 1::for creating the new User for library.
		   case 1:{
			      try {
			        System.out.println("Enter Name::");	scn.nextLine();		       
			        String name=scn.nextLine();
			        System.out.println("Enter Mobile Number::");
			        long mob=scn.nextLong();scn.nextLine();			        
			        System.out.println("Enter Email ID::");
			        String email=scn.nextLine();			        
			        System.out.println("Enter Password::");
			        String password=scn.nextLine();
			        User user=new User(name,mob,email, password);
			        library.addUser(user);
			        
			      }
			      catch(InputMismatchException e) {
			    	  System.out.println("Please Enter the Valid Information::");
			      }
		   }
		   break;
		  
		   //Case 2:: for Logging in to the already created User.
		   case 2:{
			   scn.nextLine();
			   System.out.println("Enter the userName/Mobile number::");
			   String id=scn.nextLine();
			   System.out.println("Enter the Password::");
			   String pass=scn.nextLine();			   
			   if(library.login(id, pass)) {
				   int choice1=0;
				   do {
				   System.out.println("1::Search Book");
				   System.out.println("2::Borrow Book");
				   System.out.println("3::Return Book");
				   System.out.println("4::View Book");
				   System.out.println("5::Logout");
				  
				   System.out.println("Enter your choice::");
				   choice1=scn.nextInt();
				   switch(choice1) {
				   //Inner Case 1
				   //case 1 to search the book into the library is available or not
				   case 1: {
					    System.out.println("Enter the book name::");scn.nextLine();
					    String book=scn.nextLine();
					    library.searchBook(book);
				   }
				   break;
				   //Inner Case 2
				   //case 2 for borrowing the book
				   case 2:{
					   System.out.println("Enter the book name to Borrow::");scn.nextLine();
					   String book=scn.nextLine();
					   library.borrowBook(book);
				   }
				   break;
				   //Inner case 3
				   //case 3 for Returning the book
				   case 3:{
					   System.out.println("Enter Book name::");scn.nextLine();
					   String book=scn.nextLine();
					   library.returnBook(book);
				   }
				   break;
				   case 4:{
					   library.viewBook();
				   }
				   break;
				   
				  }
				  }while(choice1!=5);
			   }
			   else {
				   System.out.println("Invalid UseName & password..");
				   System.out.println("Please try Again..");
			   }
		   }
		   break;
		   //Case 3:: for Adding new Book
		   case 3:{
			   System.out.println("Enter the book name::");scn.nextLine();
			   String bName=scn.nextLine();
			   System.out.println("Enter the Author Name::");
			   String aName=scn.nextLine();
			   Book book=new Book(bName,aName);
			   library.addBook(book);
		   }
		   break;
		   case 4:{
			  System.out.println("Enter the Book name::");scn.nextLine();
			  String name=scn.nextLine();
			  library.deleteBook(name);
		   }
		}		
		}while(choice!=5);
		System.out.println("\t\t\tThanks");
	}

}
