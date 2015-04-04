/*	EECS 448 -	Homework Assignment 4
 * 	Online BookStore Shopping Cart Simulation 
 * 	Written by : Chinmay Ratnaparkhi, Joseph Champion, Aleksander Eskilson
 * 	Instructor : Dr. Swapan Chakrabarty
 */
import java.util.Scanner;
public class Customer
{
	/* Attributes of a Customer */
	private double funds;
	private int customerId;
	private String name;
	private Book[] shoppingCart = new Book[100];
	// Number of cells occupied in the shoppingCart array by distinct Book objects.
	private int count; 
	
	/* Constructors */
	public Customer(){};
	public Customer(int customerId, String name, double funds)
	{
		setCustomerId(customerId);
		setName(name);
		setFunds(funds);
		count=0;
	}
	
	Scanner scanner = new Scanner( System.in );
	/* Adding a Book from Store Inventory to the Cart
	 * Customer is requested to enter the ID and quantity of the book desired. This
	 * information can be seen by listing all the books in the inventory. I.e. Option 1 
	 * on the Main Menu. 
	 * 
	 * If the Book already exists in the Cart, only the quantities are changed. i.e. 
	 * On the Book Object, customer quantity is raised and store quantity is reduced.
	 * Otherwise, the maximum index on the cart is increased by one and the book is placed
	 * on the new cell in the cart.  
	 */
	public void addBook(Book inv[], int safe_max)
	{
		int add_bookID, add_quant;
		System.out.print("Please enter the ID of the book : ");
			add_bookID = scanner.nextInt();
		System.out.print("Please enter the number of copies : ");
			add_quant = scanner.nextInt();
		
		// Checking if the book already exists in the cart.
		boolean flag=false;
		if(add_bookID <= safe_max)
		{	
			for(int i=0; i<count; i++)
			{
				if(shoppingCart[i].getId() == add_bookID)
				{
					flag= true;
					if(shoppingCart[i].getqStore() >= add_quant)
					{
						shoppingCart[i].setqCustomer(shoppingCart[i].getqCustomer()+add_quant);
						shoppingCart[i].setqStore(shoppingCart[i].getqStore() - add_quant);
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println("Successfully added "+add_quant+" copie(s) of '"+shoppingCart[i].getTitle()+"' to the cart." );
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println();
					}else 
					{
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println("Invalid Request : Quantity does not exist.");
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println();
					}
				}
			}
			// If flag is still false, the book does not exist in the cart.
			if(!flag)
			{
				if(inv[add_bookID].getqStore() >= add_quant)
				{
					shoppingCart[count] = inv[add_bookID];
					shoppingCart[count].setqCustomer(add_quant);
					shoppingCart[count].setqStore(shoppingCart[count].getqStore() - add_quant);
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("Successfully added "+add_quant+" copies of '"+shoppingCart[count].getTitle()+"' to the cart." );
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println();
					count++;
				}else 
				{
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("Invalid Request : Quantity does not exist.");
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println();
				}
			}
		}else 
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Invalid Request : Book ID does not exist.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
	
	/* Removing a Book from the cart.
	 * If the cart is already empty, an error message is shown. Otherwise, ID and
	 * quantity values are requested for the Book object that has to be removed. 
	 * If both ID and Quantity are appropriate, requested quantity is reduced from 
	 * the cart and added back to the bookstore. Otherwise, corresponding error 
	 * messages are shown.
	 */
	public void removeBook(){
		
		if(count==0) {
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Invalid Request : Cart is empty.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
		else
		{
			int rem_bookID, quant;
			System.out.print("Please enter the ID of the book : ");
					rem_bookID = scanner.nextInt();
			System.out.print("Please enter the number of copies : ");
					quant = scanner.nextInt();
					
			boolean found_flag= false;
			for(int i=0; i<count; i++)
			{
				if(shoppingCart[i].getId() == rem_bookID)
				{
					found_flag= true;
					if(shoppingCart[i].getqCustomer() >= quant)
					{
						shoppingCart[i].setqCustomer(shoppingCart[i].getqCustomer() - quant);
						shoppingCart[i].setqStore(shoppingCart[i].getqStore() + quant);
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println("Successfully removed "+quant+" copies of '"+shoppingCart[i].getTitle()+"' from the cart." );
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println();
					}
					else
					{
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println("Invalid Request : Quantity does not exist.");
						System.out.println("-----------------------------------------------------------------------------------------------------------");
						System.out.println();
					}
						
				}
			}
			
			if(!found_flag)
			{
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.println("Invalid Request : Book not in cart.");
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.println();
			}
		}
	};
	
	/* Evaluates the total Order Price based on the individual quantity and
	 * prices of the Book objects that are placed in the cart.
	 */
	public double calculateCost()
	{
		double costInCart=0;		
		for(int i=0; i<count; i++)
		{
			costInCart+= shoppingCart[i].getCost() * shoppingCart[i].getqCustomer(); 
		}
		return costInCart;
	}	
	/* Removes all the books from the cart of the customer and puts them back
	 * in the bookstore. Effectively, qCustomer values on the Book objects is
	 * set to 0 and qStore values are raised by however many copies of the Book 
	 * were in the cart.
	 * */
	public void emptyCart(){
		if(count==0)
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Cart is empty.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
		}
		else
		{
			for(int i=0; i<count; i++){
				shoppingCart[i].setqStore(shoppingCart[i].getqStore() + shoppingCart[i].getqCustomer());
				shoppingCart[i].setqCustomer(0);
				shoppingCart[i]= null;
			}		
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println(count+ " titles were removed from the cart. Cart is now empty.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			count=0;
		}
	}
	
	/* Calculates the total cost and compares it with the funds available for
	 * the customer. If sufficient money is available, the order is successful,
	 * Order total is REDUCED from Customer Funds and ADDED to the BookStore Funds.
	 * If funds are not available, error message is displayed.
	 *  */
	public void placeOrder(Store s){
		double orderTotal = calculateCost();
		
		if(orderTotal == 0)
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Invalid Request : Please add at least one book to place an order.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println();
			this.emptyCart();
		}
		else
		{
			if( orderTotal <= this.getFunds())
			{
				this.setFunds(this.getFunds()- orderTotal);
				for(int i=0; i<count; i++)
				{
					shoppingCart[i].setqCustomer(0);
					shoppingCart[i]= null;
				}
				count=0;
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.println("Order successful. Order Total : "+ orderTotal);
				System.out.println("Funds left : "+ this.getFunds());
				System.out.println("Thank you! Visit again!!");
				System.out.println("-----------------------------------------------------------------------------------------------------------");	
				System.out.println();
				s.addFunds(orderTotal);
			}
			else
			{
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.println("Insufficient funds. Please review your cart.");
				System.out.println("-----------------------------------------------------------------------------------------------------------");
				System.out.println();
			}
		}
	}
	
	/* Method views Funds available with the customer and lists out books 
	 * that are in the cart with quantities and prices. Also calculates the
	 * total cost of the books in the cart.
	 */
	public void viewCart(){
		if(count > 0)
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Available Funds: "+this.getFunds()+"\n");
			String categ="";
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("ID\tTitle"+"\t\t\tAuthor"+"\t\t\t\tCategory"+"\tCost");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			for(int i=0; i< count; i++)
			{
				//Category name is assigned based on the ID
				switch(shoppingCart[i].getCategoryID())
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
				//Total price calculation based on quantities and prices. 
				int qnt= shoppingCart[i].getqCustomer();
				double prc= shoppingCart[i].getCost();
				double itemTotal= prc*qnt;
				System.out.println(shoppingCart[i].getId()+"\t"+shoppingCart[i].getTitle()+"\t\t"+shoppingCart[i].getAuthor()+"\t\t"+categ+"\t"+prc+"\tX   "+qnt+"\t=   "+itemTotal);
			}
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			double totalCost= this.calculateCost();
			System.out.println("Estimated Cost (Without the Taxes) : " + totalCost);
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println();
		}else
		{
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("Available Funds: "+this.getFunds()+"\n");
			System.out.println("\t The cart is currently empty.");
			System.out.println("\t Please add Books with '3. Add a Book to Shopping Cart' on the Main Menu.");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}
	
	/* Get and Set Methods for the Customer Object */
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
