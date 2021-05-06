package main.swamy.structural.adapter;

public class MediaAdapter implements MediaPlayer {
	
	AdvancedMediaPlayer advMediaPlyr;

	public MediaAdapter(String type) {
		if(type.equalsIgnoreCase("VLCC")){
			advMediaPlyr = new VlccPlayer();
		}else if(type.equalsIgnoreCase("MP4")){
			advMediaPlyr = new Mp4Player();
		}
	}

	@Override
	public void play(String type, String fileName) {
		if(type.equalsIgnoreCase("vlcc")){
			advMediaPlyr.playVlcc(fileName);
		}else if(type.equalsIgnoreCase("mp4")){
			advMediaPlyr.playMp4(fileName);
		}else{
			
		}
		

	}

}
