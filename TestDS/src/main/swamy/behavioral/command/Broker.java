package main.swamy.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the invoker of the command based on request.
 * this will decide which command to execute
 * 
 * 
 */
public class Broker {
	
	private List<Order> orderList = new ArrayList<Order>();

	public Broker() {
		
	}

	public void takeOrder(Order order){
		orderList.add(order);
	}
	
	public void placeorder(){
		for(Order o: orderList){
			o.execute();
		}
		orderList.clear();
		
	}
}
