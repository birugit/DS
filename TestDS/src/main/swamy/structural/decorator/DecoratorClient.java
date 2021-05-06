package main.swamy.structural.decorator;

public class DecoratorClient {

	public DecoratorClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Shape circle = new Circle();
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		System.out.println("**Normal Circle**");
		circle.draw();
		
		System.out.println("**Red Rectangle**");
		redRectangle.draw();
		
		System.out.println("**Red Circle**");
		redCircle.draw();

	}

}
