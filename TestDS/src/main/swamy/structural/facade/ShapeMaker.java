package main.swamy.structural.facade;

public class ShapeMaker {

	Shape circle;
	Shape rectangle;
	public ShapeMaker() {
		circle = new Circle();
		rectangle = new Rectangle();
	}
	
	public void drawCircle(){
		circle.draw();
	}
	
	public void drawRectangle(){
		rectangle.draw();
	}

}
