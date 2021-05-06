package main.swamy.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator {

	
	public RedShapeDecorator(Shape shape) {
		super(shape);
		
	}
	
	@Override
	public void draw(){
		shape.draw();
		setRedShape(shape);
	}

	private void setRedShape(Shape shape) {
		System.out.println("Red Colcor");
		
	}

}
