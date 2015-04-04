/*	EECS 448 -	Homework Assignment 4
 * 	Online BookStore Shopping Cart Simulation 
 * 	Written by : Chinmay Ratnaparkhi, Joseph Champion, Aleksander Eskilson
 * 	Instructor : Dr. Swapan Chakrabarty
 */
import java.util.Scanner;
public class Test
{
	public static String booksList = "http://people.eecs.ku.edu/~mmishra/EECS_448_Fall2014/Book-List-For-EECS-HW4.txt";
	public static void main(String [] args)
	{
		// A BookStore and a Customer are created
		Store DustyBookShelf = new Store();
		DustyBookShelf.readDatabase(booksList);
		
		Customer Joe = new Customer(207, "Joe" , 1000);

		int select= -1;
		Scanner scanner = new Scanner( System.in );
		while(select != 99)
		{
			System.out.println("Please select an action : \n\t1. View Inventory\t\t\t2. View cart \n\t3. Add a Book to Shopping Cart\t\t4. Remove a Book from Shopping Cart\n\t5. Empty Cart \t\t\t\t6. Place Order\n\t7. Log In as a Manager\t\t\t8. Cancel and Quit");
			System.out.print( "Selection: " );
			select = scanner.nextInt();
			switch(select)
			{
				case 1: 
					DustyBookShelf.listBooks();
					break;
				case 2: 
					Joe.viewCart();
					break;
				case 3: 
					Joe.addBook(DustyBookShelf.inventory, DustyBookShelf.inv_count);
					break;
				case 4 : 
					Joe.removeBook();
					break;
				case 5 : 
					Joe.emptyCart();
					break;	
				case 6 :
					Joe.placeOrder(DustyBookShelf);
					break;
				case 7 : 
					DustyBookShelf.supply();
					break;
				case 8 :
					Joe.emptyCart();
					System.out.println("----------------------------------------------------------------------------------------------");
					System.out.println("Thank you! Visit again!!");
					System.out.println("----------------------------------------------------------------------------------------------");
					select= 99;
					break;
				default : 
					System.out.println("----------------------------------------------------------------------------------------------");
					System.out.println("Invalid Entry. Try again.");
					System.out.println("----------------------------------------------------------------------------------------------");
					break;
			}
		}
		scanner.close();
	}
}
