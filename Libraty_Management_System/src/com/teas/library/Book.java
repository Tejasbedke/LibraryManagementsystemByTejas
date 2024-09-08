package com.teas.library;

public class Book {
	
   private String name;
   private String Author;
   
   public Book(String name,String Author) {
	   this.name=name;
	   this.Author=Author;
   }
   
   //Setter and Getter for the Book class

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return Author;
}
public void setAuthor(String author) {
	Author = author;
}
   
   
}
