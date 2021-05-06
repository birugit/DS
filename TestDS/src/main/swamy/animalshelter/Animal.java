package main.swamy.animalshelter;

public abstract class Animal {
	private int order;
	protected String name;
	public Animal(String n){
		name = n;
	}
	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	public boolean isOlderThan(Animal a){
		return this.order < a.getOrder();
	}
	
	public String toString(){
		return name;
	}
}
