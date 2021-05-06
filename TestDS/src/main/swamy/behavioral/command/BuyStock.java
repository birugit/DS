package main.swamy.behavioral.command;
/**
 * 
 * @author Swamy
 *This class is concrete implementation of command
 */
public class BuyStock implements Order {
	private Stock stockObj;

	public BuyStock(Stock stock) {
		this.stockObj = stock;
	}

	@Override
	public void execute() {
		
		stockObj.buy();
	}

}
