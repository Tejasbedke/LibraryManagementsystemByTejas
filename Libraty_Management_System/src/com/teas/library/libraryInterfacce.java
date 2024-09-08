package com.teas.library;

public interface libraryInterfacce {
	
	//Book Management Methods
	public void addBook(Book book);
	public void viewBook();
	public void deleteBook(String id);
	
    //User management Methods
	public void addUser(User user);
	public void deleteUser();
	public void viewUser();
	
	//Book and user Interaction methods
	public void borrowBook(String name);
	public void returnBook(String name);
	
	//search Book methods 
    public void searchBook(String name);
    public void searchBook(int id);
    
    //User Login method
    public boolean login(String userName,String password);
    public void logout();
	
	
}
