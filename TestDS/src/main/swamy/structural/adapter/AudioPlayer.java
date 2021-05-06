package main.swamy.structural.adapter;

public class AudioPlayer implements MediaPlayer {
	MediaAdapter mediaAdapter;
	public AudioPlayer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(String type, String fileName) {
		if(type.equals("mp3")){
		System.out.println("Playing MP3:"+fileName);
		}else if(type.equalsIgnoreCase("vlcc") || type.equalsIgnoreCase("mp4")){
			mediaAdapter = new MediaAdapter(type);
			mediaAdapter.play(type, fileName);
		}else{
			System.out.println("Invlid music type:"+type);
		}

	}

}
