package com.teas.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LibraryManagementSystem implements libraryInterfacce{

	private static final String jdbc_url="jdbc:mysql:///projecttable";
	private static final String jdbc_user="root";
	private static final String jdbc_pass="tejas@4793";
	
	private static final String ADD_USER="INSERT INTO LIBRARYMANAGEMENTSYSTEM (USER_NAME,USER_EMAIL,MOBILE,PASSWORD) VALUES(?,?,?,?)";
    private static final String SELECT_BOOK="";
    private static final String LOGIN_QUERY="SELECT COUNT(*) FROM LIBRARYMANAGEMENTSYSTEM WHERE USER_NAME=? AND PASSWORD=?";
	@Override	
    public void  addUser(User user) {
    	int id=0;
    	try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
    		PreparedStatement ps=con.prepareCall(ADD_USER);
    		){    			
    		  
    		   ps.setString(1,user.getName());
    		   ps.setString(2, user.getEmail());
    		   ps.setLong(3,user.getMob());
    		   ps.setString(4, user.getPassword());
    		   int count=ps.executeUpdate();
    		   if(count==0) {
    			   System.out.println("Account not created ");
    			   System.out.println("Please try Again");
    		   }else
    			   System.out.println("Account Created Successfully.");    			   
    		}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    
    	    	
    }

	@Override
	public boolean login(String userName, String password) {
		int count=0;
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
				PreparedStatement ps=con.prepareCall(LOGIN_QUERY);
				){    
			ps.setString(1,userName);
			ps.setString(2,password);
			
			try(ResultSet rs=ps.executeQuery();){
				rs.next();
				count=rs.getInt(1);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(count==1)
			return true;
		else
			return false;
		
	}

	@Override
	public void addBook(Book book) {
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
				PreparedStatement ps=con.prepareStatement("INSERT INTO BOOK_COLLECTION (BOOK_NAME,AUTHOR_NAME,AVAILABLE) VALUES(?,?,?)");				
				 ){
			ps.setString(1,book.getName());
			ps.setString(2,book.getAuthor());
			ps.setString(3,"true");
			
			int count=ps.executeUpdate();
			if(count==0) 
				System.out.println("Book Not inserted\n Something went wrong");				
			else
				System.out.println("Book inerted Successfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void searchBook(String name) {
		
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
				PreparedStatement ps = con.prepareStatement("SELECT * FROM BOOK_COLLECTION WHERE BOOK_NAME LIKE ?");				
				 ){
			if(ps!=null) {
				ps.setString(1, name+"%");
				
				try(ResultSet rs=ps.executeQuery();){
		    		ResultSetMetaData md=rs.getMetaData();
					
					if(rs!=null) {
						if(rs.next()!=false) {
							
						System.out.println(md.getColumnLabel(1)+"\t"+md.getColumnName(2)+"\t"+md.getColumnName(3)+"\t"+md.getColumnName(4));
					    do {
						System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t\t"+rs.getString(4));
					    }while(rs.next());
						}
						else 
							System.out.println("No items found");
					}
					
					System.out.println("\n\n");
				}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewBook() {
		
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("SELECT * FROM BOOK_COLLECTION");){
			if(rs!=null) {
				if(rs.next()!=false) {
		    	ResultSetMetaData md=rs.getMetaData();

				System.out.println(md.getColumnLabel(1)+"\t"+md.getColumnName(2)+"\t"+md.getColumnName(3)+"\t"+md.getColumnName(4));
				 do {
				  System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
				}while(rs.next());
				}
				else 
					System.out.println("No items found");
			}
			
			System.out.println("\n\n");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void borrowBook(String name) {
		
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
			PreparedStatement ps=con.prepareStatement("SELECT COUNT(*) FROM BOOK_COLLECTION WHERE BOOK_NAME=? AND AVAILABLE='TRUE'");){
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs!=null)
			rs.next();
			int count=rs.getInt(1);
			if(count>=1) {
				System.out.println("Book is Available");
				try(Statement  st=con.createStatement();
						){
					int count1=st.executeUpdate("UPDATE BOOK_COLLECTION SET AVAILABLE=FALSE WHERE BOOK_NAME=n'"+name+"'");
					if(count1>=1) {
						System.out.println("Book is Borrowed");
					}else
						System.out.println("Please try again");
				}
			}else {
				System.out.println("Book is not Available");
			}
			System.out.println("\n\n");
		}catch(SQLException e) {
			e.printStackTrace();
		}
					
					
	}

	@Override
	public void deleteBook(String name) {
		
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
			PreparedStatement ps=con.prepareStatement("DELETE FROM BOOK_COLLECTION WHERE BOOK_NAME=?");){
			if (ps!=null) {
				
				ps.setString(1,name);
				
				int count=ps.executeUpdate();
				
				if(count>=1)
					System.out.println("Book Deleted ");
				else
					System.out.println("Book Not Found");
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser() {
		
		
	}

	@Override
	public void viewUser() {
		
	}
	@Override
	public void returnBook(String name)  {
		try(Connection con=DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_pass);
			PreparedStatement ps=con.prepareStatement("UPDATE BOOK_COLLECTION SET AVAILABLE='TRUE' WHERE BOOK_NAME=? AND AVAILABLE='0'")){
			if(ps!=null) {
				ps.setString(1,name);
				int count=ps.executeUpdate();
				if(count>=1)
					System.out.println("Book Returned ");
				else
					System.out.println("Something went wrong");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void searchBook(int id) {
		
		
	}
	@Override
	public void logout() {
		
	}

}
