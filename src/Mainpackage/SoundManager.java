package Mainpackage;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class SoundManager {
	final static String datafolder = Mainclass.datafolder;
	public Klip Menumusic;
	public Klip GameMusic;
	public Klip dreckdig;
	public Klip holzdig;
	public Klip bletterdig;
	public Klip stonedig;
	public Klip schneelauf;
	public SoundManager(){}
	public SoundManager loadSounds(){
		try{
			Clip GameMusicc;
			Clip Menumusicc;
			Clip dreckdigg;
			Clip holzdigg;
			Clip bletterdigg;
			Clip stonedigg;
			Clip schneelauff;
			GameMusicc = loadAudioFile(datafolder + "\\Overworld.wav");
			Menumusicc = loadAudioFile(datafolder + "\\Menumusic.wav");
			dreckdigg = loadAudioFile(datafolder + "\\dirtdig.wav");
			holzdigg = loadAudioFile(datafolder + "\\holzdig.wav");
			stonedigg = loadAudioFile(datafolder + "\\stonedig.wav");
			bletterdigg = loadAudioFile(datafolder + "\\bletterdig.wav");
			schneelauff = loadAudioFile(datafolder + "\\Laufen.wav");
			dreckdig = new Klip(dreckdigg);
			schneelauf = new Klip(schneelauff);
			holzdig = new Klip(holzdigg);
			stonedig = new Klip(stonedigg);
			bletterdig = new Klip(bletterdigg);
			Menumusic = new Klip(Menumusicc);
			GameMusic = new Klip(GameMusicc);
		}
		catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		return this;
	}
	public static Clip loadAudioFile(String s) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(s));
			AudioFormat af = audioInputStream.getFormat();
			int size = (int) (af.getFrameSize() * audioInputStream
					.getFrameLength());
			byte[] audio = new byte[size];
			DataLine.Info info = new DataLine.Info(Clip.class, af, size);
			audioInputStream.read(audio, 0, size);

			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			return clip;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void playSound(Klip c){
		
		c.cl.setMicrosecondPosition(0);
		c.cl.start();
	}
	public void stopSound(Klip c) throws NullPointerException{
		c.stopLooping();
		c.cl.setMicrosecondPosition(0);
	}
	public void modifyVolume(Klip c,int kakor){
		  FloatControl gc = (FloatControl)(c.cl.getControl(FloatControl.Type.MASTER_GAIN));
		  gc.setValue(kakor);
		 }
	public void loopSound(Klip c){
		c.startLooping();
	}
}
