package main.swamy.minstack;

public class NodeWithMin {
	
	public int value;
	public int min;
		public NodeWithMin(int value, int min){
			this.value = value;
			this.min = min;
		}
		
		public String toString(){
			System.out.println("Value:"+value +" MinValue:"+min);
			return value+":"+min;
		}

}
