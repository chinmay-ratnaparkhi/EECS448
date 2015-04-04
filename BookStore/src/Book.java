/*	EECS 448 -	Homework Assignment 4
 * 	Online BookStore Shopping Cart Simulation 
 * 	Written by : Chinmay Ratnaparkhi, Joseph Champion, Aleksander Eskilson
 * 	Instructor : Dr. Swapan Chakrabarty
 */
public class Book {
	
	/* Attributes */	
	private double cost;
	private int id;
	private String title;
	private String author;
	private int categoryID;
	
	/* Each Book knows how many of its copies are in Store and 
	 * in a customer's shopping cart.
	 */
	private int qStore;
	private int qCustomer;
	
	// Constructors
	public Book(){}
	public Book( int id, String title, String author, int categoryID, int qStore)
	{	
		setId(id);
		setTitle(title);
		setAuthor(author);
		setCategoryID(categoryID);
		setqStore(qStore);
		this.qCustomer = 0;
			
		/*Every book in a particular category has the same price.*/
		switch(categoryID)
		{
			case 0: setCost(10);
			break;	
			case 1: setCost(15);
			break;
			case 2: setCost(20);
			break;
			case 3: setCost(25);
			break;
			case 4: setCost(30);
			break;
			case 5: setCost(50);
			break;
			case 6: setCost(40);
			break;
			default : setCost(-1);
			case 7: setCost(100);
			break;
		}
	}
	
	/* Get & Set methods for the attributes */
	public double getCost() {
		return cost;
	}
	private void setCost(double cost) {
		this.cost = cost;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getqStore() {
		return qStore;
	}
	public void setqStore(int qStore) {
		this.qStore = qStore;
	}
	public int getqCustomer() {
		return qCustomer;
	}
	public void setqCustomer(int qCustomer) {
		this.qCustomer = qCustomer;
	}
}
