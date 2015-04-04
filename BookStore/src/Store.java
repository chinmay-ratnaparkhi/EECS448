/*	EECS 448 -	Homework Assignment 4
 * 	Online BookStore Shopping Cart Simulation 
 * 	Written by : Chinmay Ratnaparkhi, Joseph Champion, Aleksander Eskilson
 * 	Instructor : Dr. Swapan Chakrabarty
 */
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Store {
	
	/* Attributes of the Store */
	public Book[] inventory = new Book[100]; 
	public Scanner reader= new Scanner(System.in);
	double funds;
	public int inv_count;
	int MAX =20;	//While Refilling the inventory, this is the max number of copies per book.
	
	
	/* constructors */
	Store(){ inv_count= 0; funds=10000;}
	Store(double funds, Book[] books, int inv_count){
		this.funds = funds;
		for(int i=0; i< inv_count; i++){
			this.inventory[i] = books[i];
			inv_count= i;
		}
	};
	
	/* Adds Funds to the BookStore
	 * When a customer successfully completes an order, the order
	 * total is added to the funds of the bookstore.
	 */
	public void addFunds(double amount)
	{
		this.funds+= amount;
	}
	

	/* Add a Book Object to the Inventory
	 * If the Book ID already exists in the inventory, the quantity is increased
	 * otherwise, a new book is placed on the inventory and the inventory count is
	 * raised.
	 */
	public void addBook(Book toBeAdded, int quantity){
		for(int i=0; i< inv_count; i++){
			if(inventory[i].getId() == toBeAdded.getId()){
					inventory[i].setqStore(inventory[i].getqStore() + quantity);
			}
		}
		inventory[inv_count] = toBeAdded;
		inventory[inv_count].setqCustomer(quantity);
		inventory[inv_count].setqStore(inventory[inv_count].getqStore() + quantity);
		inv_count++;
	}
			
	/* Lists all the Book objects available in the Inventory. 
	 * ID, Title, Author, Category (Name), Price and available number of copies
	 * are displayed in tabular formatting. 
	 */
	public void listBooks(){
		if(inv_count == 0)
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println("\tInventory is currently empty.");
			System.out.println("\tUse '7. Log In as a Manager' to add books to the store inventory.");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
		else 
		{
			String categ="";
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.printf("%-4s%-65s%-26s%-14s%-4s\n", "ID","Title","Author","Category","Cost","Available");
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			for(int i=0; i< inv_count; i++)
			{
				switch(inventory[i].getCategoryID())
				{
					case 0: categ= "Fiction       ";
						break;
					case 1: categ= "Novel         ";
						break;
					case 2: categ= "Biography     ";
						break;
					case 3: categ= "Encyclopedia  ";
						break;
					case 4: categ= "Reference     ";
						break;
					case 5: categ= "SciFi         ";
						break;
					case 6: categ= "Travel        ";
						break;
					case 7: categ= "Software Eng  ";
						break;
				}
				System.out.printf("%-4s%-65s%-26s%-14s%-4s\n", inventory[i].getId(), inventory[i].getTitle(), inventory[i].getAuthor(), categ, inventory[i].getCost(), inventory[i].getqStore());
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
	
	/* 		Manager Mode
	 * A Manager can Log In from the main menu to make changes to the inventory.
	 * Funds available in the bookstore are displayed. 
	 * 1. All the records can be listed,
	 * 2. sold items can be replaced, values are restored to 20.
	 * 3. A new book can be created.
	 * 4. Standard set of books can be loaded to the inventory. 
	 * 5. Log Out and Return to the Main Menu.
	 */
	public void supply()
	{
		int select= -1;
		while(select!= 99)
		{
			System.out.println("\n----------------------------------------------------------------------------------------------");
			System.out.println("Available Funds: "+this.funds);
			System.out.println("----------------------------------------------------------------------------------------------");
			System.out.println("Please select an action : \n\t1. View Inventory \t\t2. Refill Inventory\n\t3. Add A New Book \t\t4. Load Standard Inventory \n\t5. Manager Log Out");
		    System.out.print( "Selection: " );
		    select = reader.nextInt();
		    switch(select)
		    {
		    	case 1: 
		    		this.listBooks();
		    		break;
		    	case 2: 
		    		for(int i=0; i< inv_count; i++) this.inventory[i].setqStore(MAX);
		   			break;
		    	case 3: 
		    		this.addNew();
	    			break;
		    	case 4 : 
		    		this.loadSTD();
		    		break;
		    	case 5 : 
		    		System.out.println("-----------------------------------------------------------------------------------------------------------");
		    		System.out.println("Thank you! Logging Out...");
		    		System.out.println("-----------------------------------------------------------------------------------------------------------");
		    		System.out.println();
		    		select= 99;
	    			break;
		    	default : 
		    		System.out.println("-----------------------------------------------------------------------------------------------------------");
		    		System.out.println("Invalid Entry. Try again.");
		    		System.out.println("-----------------------------------------------------------------------------------------------------------");
		    		System.out.println();
		    		break;
			  }
		}
	}
	
	/* Create a new Book and add to Inventory
	 * Manager enters information for various attributes of a book, they are listed out,
	 * if the manager is satisfied, a new book object is created with the entered values
	 * and is added to the inventory. Otherwise, Manager is prompted to enter with values
	 * again.
	 */
	public void addNew()
	{	
		String new_title, new_author;
		int new_cat, new_copies, select;	
		boolean verified=false;
		while(!verified)
		{
			reader.nextLine();	
		    System.out.print( "\nTitle: " );
		    	new_title = reader.nextLine();	
			System.out.print( "Author: " );
				new_author = reader.nextLine();
		    System.out.print( "Category: " );
		    	new_cat = reader.nextInt();
		    System.out.print( "Copies: " );
		    	new_copies = reader.nextInt();
		    	
		    	//Check with the manager if the values are acceptable.
		    	System.out.println("Title"+"\t\t\tAuthor"+"\t\t\t\tCategory"+"\tAvailable Copies");
		    	System.out.println("-----------------------------------------------------------------------------------------------------------");	
				System.out.println(new_title+"\t"+new_author+"\t\t"+new_cat+"\t\t"+new_copies);
				System.out.println("\nDoes everything look alright? Enter '1' for yes, '2' for no.");
				select= reader.nextInt();
				if(select==1)
				{
					verified= true;
					Book toBeAdded= new Book(this.inv_count, new_title, new_author, new_cat, 0);
					this.addBook(toBeAdded, new_copies);
					
				}else if(select==2) 
				{
					verified= false;
				}
				else 
				{
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("Invalid Entry. Try again!"); 
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println();
				}
		}	
	}
	
	/*	Load Standard 
	 *  Empties out the inventory and loads a sample set of books. Mainly for
	 *  testing purposes.
	 */
	public void loadSTD()
	{
		for(int i=0; i<inv_count; i++){
			inventory[i]= null;
		}
		inv_count=0;
		Book toBeAdded1= new Book(inv_count, "Hunger Games", "Lisa Val Kudrow ", 0, 0);
		this.addBook(toBeAdded1, 20);
		Book toBeAdded2= new Book(inv_count, "Harry Potter", "Joseph. K. Smith", 0, 0);
		this.addBook(toBeAdded2, 20);
		Book toBeAdded3= new Book(inv_count, "Game of Thrones", "Sofia Vergara J.", 1, 0);
		this.addBook(toBeAdded3, 20);
		Book toBeAdded4= new Book(inv_count, "Twilight Saga", "Jennifer Aniston", 1, 0);
		this.addBook(toBeAdded4, 20);
		Book toBeAdded5= new Book(inv_count, "Pride and Prej", "William McFarland", 2, 0);
		this.addBook(toBeAdded5, 20);	
		Book toBeAdded6= new Book(inv_count, "Winter Stories", "Debra Messing J.", 2, 0);
		this.addBook(toBeAdded6, 20);	
		Book toBeAdded7= new Book(inv_count, "Mysteries of L", "Neil Patrick Har", 3, 0);
		this.addBook(toBeAdded7, 20);	
		Book toBeAdded8= new Book(inv_count, "The Big Bang T", "Matth Cumberbatch", 3, 0);
		this.addBook(toBeAdded8, 20);	
		Book toBeAdded9= new Book(inv_count, "The Hobbit Dev", "Jared Letto Paul", 4, 0);
		this.addBook(toBeAdded9, 20);	
		Book toBeAdded10= new Book(inv_count, "Learning Java", "Harrison Cooper F", 4, 0);
		this.addBook(toBeAdded10, 20);	
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("\tLoaded Standard Inventory Items"); 
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println();
	}
	/*	Read Database
	 *  Pulls the database text file from a given URL string. Parses the database
	 *  based on its known format into a collection of new books
	 */
	public void readDatabase(String address) 
	{
		// Regular Expression matching the format of the database text file
		Pattern bookPattern = Pattern.compile("^\\d+\\.?\\t([\\w+| |\\p{Punct}]*)\\s+([\\w+| |\\p{Punct}]*).*$");
		
		// Initialize inventory
		for(int i=0; i<inv_count; i++){
			inventory[i]= null;
		}
		inv_count=0;
		
		// Read URL, parse database into inventory
		try 
		{
			URL databaseURL = new URL(address);
			Scanner s = new Scanner(databaseURL.openStream());
			
			String line;
			int count= 1;
			int category= 5;
			// Match lines, parse into a book
			while(s.hasNext()){
				line = s.nextLine();
				Matcher candidate = bookPattern.matcher(line);
				
				if(candidate.matches()){
					if(count==11)
						category= 6;
					if(count==21)
						category= 7;
					
					Book toBeAdded= new Book(inv_count, candidate.group(2), candidate.group(1), category, 0);
					this.addBook(toBeAdded, 20);
					
					count++;
				}
			}
			
			s.close();
		} 
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
}
