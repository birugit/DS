package main.swamy.behavioral.command;

public class CommandClient {

	public CommandClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Stock stock = new Stock();
		Broker broker = new Broker();
		
		BuyStock  buyStock = new BuyStock(stock);
		SellStock sellStock = new SellStock(stock);
		
		broker.takeOrder(buyStock);
		broker.takeOrder(sellStock);
		
		
		broker.placeorder();
				

	}

}
