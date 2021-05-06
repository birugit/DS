package main.swamy.structural.facade;

public class FacadeClient {

	public FacadeClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ShapeMaker shapeMaker = new ShapeMaker();
		shapeMaker.drawCircle();
		shapeMaker.drawRectangle();

	}

}
