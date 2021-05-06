package main.swamy.structural.adapter;

public class AdapterClient {

	public AdapterClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		AudioPlayer adPlayer = new AudioPlayer();
		adPlayer.play("vlcc", "Hips Dont Lie");
		adPlayer.play("mp4", "Roar");
		adPlayer.play("mp3", "Shahensha");
		adPlayer.play("mp5", "Hips Dont Lie");

	}

}
