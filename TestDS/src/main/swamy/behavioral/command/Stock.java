package main.swamy.behavioral.command;
/**
 * 
 * @author Swamy
 *This class is a request wrapped in object passed to command
 */
public class Stock {
	
	private String stock = "ABC";
	private int qty = 10;

	public Stock() {
		
	}

	public void sell(){
		System.out.println("Stock:"+stock +" Sold qty:"+qty);
	}
	
	public void buy(){
		System.out.println("Stock:"+stock +" bought qty:"+qty);
	}
}
