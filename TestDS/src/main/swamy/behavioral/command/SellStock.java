package main.swamy.behavioral.command;

public class SellStock implements Order {
	private Stock stockObj;
	public SellStock(Stock stock) {
		this.stockObj = stock;
	}

	@Override
	public void execute() {
		
		stockObj.sell();
	}

}
