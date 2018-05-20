package Mainpackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import Blocks.Bletter;
import Blocks.Dreck;
import Blocks.Eisen;
import Blocks.Felder;
import Blocks.Holz;
import Blocks.Luft;
import Blocks.Schneedecke;
import Blocks.Stone;
import Blocks.Türoben;
import Blocks.Türunten;
import Blocks.schneedreck;
import Effekte.Schnee;

public class Map {
	static void init() {
		for (int i = 0; i < Mainclass.xsize; i++) {
			for (int b = 0; b < Mainclass.ysize; b++) {
				float random = (float) (Math.random());
				Mainclass.array1[i][b] = new Felder(i, b, Mainclass.blockgrösse);
				if (b < 30) {
					Mainclass.array1[i][b].ersetzen(new Luft());
				}
				if (b < 55 && b > 30 && random < 0.1f) {
					Mainclass.array1[i][b].ersetzen(new Stone());
				}
				if (b > 55) {
					Mainclass.array1[i][b].ersetzen(new Stone());
				}
				float random1 = (float) (Math.random());
				if (b > 30 && random1 < 0.06f) {
					Mainclass.array1[i][b].ersetzen(new Eisen());
				}
				if (b == 30) {
					Mainclass.array1[i][b].ersetzen(new schneedreck());
				}
			}
		}
		for (int i = 0; i < Mainclass.xsize; i++) {
			float random = (float) (Math.random());
			if (random < 0.1f && i > 4 && i < 396 && Mainclass.array1[i][31].content.getClass().equals(new Dreck().getClass())
					&& !Mainclass.array1[i - 1][29].content.getClass().equals(new Holz().getClass()) && !Mainclass.array1[i + 1][29].content.getClass().equals(new Holz().getClass())) {
				Map.baum(i, 29);
			}
		}
		Mainclass.Menustatus = 0;
	}

	public static void speichern() {
		try {
			FileOutputStream fout = new FileOutputStream("save");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(Mainclass.array1);
			oos.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void laden() {
		try {
			FileInputStream fin;
			fin = new FileInputStream("Lukas");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Mainclass.array1 = (Felder[][]) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Mainclass.Menustatus = 0;
	}

	public static void baum(int xpos, int ypos) {
		Mainclass.array1[xpos][ypos].ersetzen(new Holz());
		Mainclass.array1[xpos][ypos - 1].ersetzen(new Holz());
		Mainclass.array1[xpos][ypos - 2].ersetzen(new Holz());
		Mainclass.array1[xpos + 1][ypos].ersetzen(new Holz());
		Mainclass.array1[xpos + 1][ypos - 1].ersetzen(new Holz());
		Mainclass.array1[xpos + 1][ypos - 2].ersetzen(new Holz());
		Mainclass.array1[xpos][ypos - 3].ersetzen(new Bletter());
		Mainclass.array1[xpos + 1][ypos - 3].ersetzen(new Bletter());
		Mainclass.array1[xpos - 1][ypos - 3].ersetzen(new Bletter());
		Mainclass.array1[xpos + 2][ypos - 3].ersetzen(new Bletter());
		Mainclass.array1[xpos - 2][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos + 1][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos - 1][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos + 2][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos + 3][ypos - 4].ersetzen(new Bletter());
		Mainclass.array1[xpos - 2][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos + 1][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos - 1][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos + 2][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos + 3][ypos - 5].ersetzen(new Bletter());
		Mainclass.array1[xpos - 2][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos + 1][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos - 1][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos + 2][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos + 3][ypos - 6].ersetzen(new Bletter());
		Mainclass.array1[xpos][ypos - 7].ersetzen(new Bletter());
		Mainclass.array1[xpos + 1][ypos - 7].ersetzen(new Bletter());
		Mainclass.array1[xpos - 1][ypos - 7].ersetzen(new Bletter());
		Mainclass.array1[xpos + 2][ypos - 7].ersetzen(new Bletter());
	}

	public static void schneesturm() {
		int xrandome;
		for (int i = 0; i < 5; i++) {
			xrandome = (int) (Math.random() * 3000 - 700);
			Mainclass.schnees.add(new Schnee(xrandome - Mainclass.xver, -10, 10));
		}
		for (int i = 0; i < Mainclass.schnees.size(); i++) {
			if (Mainclass.schnees.get(i).yp > 1080) {
				Mainclass.schnees.remove(i);
			}
		}
	}

	public static void update() {
		for (int i = 0; i < Mainclass.xsize; i++) {
			for (int b = 0; b < Mainclass.ysize; b++) {
				if (Mainclass.array1[i][b].content.getClass().equals(new Türunten().getClass()) && !Mainclass.array1[i][b - 1].content.getClass().equals(new Türoben().getClass())) {
					Mainclass.array1[i][b].ersetzen(new Luft());
				}
				if (Mainclass.array1[i][b].content.getClass().equals(new Türoben().getClass()) && !Mainclass.array1[i][b + 1].content.getClass().equals(new Türunten().getClass())) {
					Mainclass.array1[i][b].ersetzen(new Luft());
				}
				if (Mainclass.array1[i][b].content.getharte() <= 0){
					Mainclass.array1[i][b].ersetzen(new Luft());
				}
			}
		}
	}
}
