package Mainpackage;
import javax.sound.sampled.Clip;

public class Klip {
	boolean looping;
	Clip cl;
	Clip urc;
	Klip(Clip c){
		cl = c;
		looping = false;
		urc = c;
	}
	public void startLooping(){
		if(!looping){
			cl.loop(Clip.LOOP_CONTINUOUSLY);
			looping = true;
		}
		else{}
	}
	public void stopLooping() throws NullPointerException{
		looping = false;
		cl.stop();
		//cl.setMicrosecondPosition(0);
	}
}
