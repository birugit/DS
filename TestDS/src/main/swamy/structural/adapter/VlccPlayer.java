package main.swamy.structural.adapter;

public class VlccPlayer implements AdvancedMediaPlayer {

	public VlccPlayer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playVlcc(String fileName) {
		System.out.println("Playing VLCC");

	}

	@Override
	public void playMp4(String fileName) {
		// TODO Auto-generated method stub

	}

}
