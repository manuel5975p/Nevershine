package Mainpackage;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import Blocks.Bletter;
import Blocks.Dreck;
import Blocks.Eisen;
import Blocks.Feuerstelle;
import Blocks.Holz;
import Blocks.Luft;
import Blocks.Stone;
import Blocks.Türoben;
import Blocks.Türunten;
import Blocks.schneedreck;

public class Spieler {
	int yp, grösse, xp, gesch, px, py, leben, maxleben, usecooldown2, jumpp, keyspace = 0, falls = 0;
	boolean oben, unten, rechts, links, jump = false, inventarcooldown = false;

	Spieler(int x, int y, int g, int l, int ml) {
		xp = x;
		yp = y;
		gesch = g;
		leben = l;
		maxleben = ml;
	}

	public void anzeigen() {
		GL11.glColor4f(1, 1, 1, 1);
		if (Mainclass.facing == 1) {
			Mainclass.faceleft.displayRect(xp + Mainclass.xver, yp + Mainclass.yver, 26, 50);
		}
		if (Mainclass.facing == 2) {
			Mainclass.face.displayRect(xp + Mainclass.xver, yp + Mainclass.yver, 26, 50);
		}
		if (Mainclass.facing == 3) {
			Mainclass.faceright.displayRect(xp + Mainclass.xver, yp + Mainclass.yver, 26, 50);
		}
	}

	public void bewegen() {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			colisionlinks();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A) && links == false) {
			xp -= gesch;
			Mainclass.facing = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			colisionRechts();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) && rechts == false) {
			xp += gesch;
			Mainclass.facing = 3;
		}
		colisionOben();
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && oben == false && unten == true && keyspace == 0) {
			jump = true;

		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Mainclass.facing = 2;
		}
	}

	public void jump() {
		if (jump == true && oben == false) {
			Mainclass.spielers.get(0).yp -= Mainclass.jumps;
			jumpp += Mainclass.jumps;
			keyspace += 1;
		}
		if (jumpp >= Mainclass.jumph) {
			jumpp = 0;
			jump = false;
		}
		if(oben == true){
			jumpp = 0;
			jump = false;
		}
	}

	public void fall() {
		if(jump == false){
			colisionUnten();
			
			falls += 1;
			
			if(falls > 11){
				falls = 11;
			}
		}
		if (jump == false && unten == false) {
			Mainclass.spielers.get(0).yp += falls;
			System.out.println(falls);
		}
	}

	public void colisionOben() {
		// oben
		int move = 0;
		oben = false;

		for (int r = 1; r < falls + 1; r++) {
			if (Mainclass.array1[(xp)/ Mainclass.blockgrösse]
								[(yp - r +1) / Mainclass.blockgrösse].content.begehbar() == false && oben == false) {
				oben = true;
				move = r;
			}
			if (Mainclass.array1[(xp + 25)/ Mainclass.blockgrösse]
								[(yp - r +1) / Mainclass.blockgrösse].content.begehbar() == false && oben == false) {
				oben = true;
				move = r;
			}
		}
		
		if(oben == true){
			yp -= move -1;
		}
	}

	public void colisionUnten() {
		// unten
		int move = 0;
		unten = false;

		for (int r = 1; r < falls + 1; r++) {
			if (Mainclass.array1[(xp)/ Mainclass.blockgrösse]
								[(yp + 50 + r -1) / Mainclass.blockgrösse].content.begehbar() == false && unten == false) {
				unten = true;
				move = r;
				falls = 0;
			}
			if (Mainclass.array1[(xp + 25)/ Mainclass.blockgrösse]
								[(yp + 50 + r -1) / Mainclass.blockgrösse].content.begehbar() == false && unten == false) {
				unten = true;
				move = r;
				falls = 0;
			}
		}
		
		if(unten == true){
			yp += move -1;
		}
		if(keyspace > 2){
			keyspace = 0;
		}
	}

	public void colisionRechts() {
		// rechts
		int move = 0;
		rechts = false;

		for (int r = 1; r < Mainclass.speed + 1; r++) {
			if (Mainclass.array1[(xp + r + 25)/ Mainclass.blockgrösse]
								[(yp +1) / Mainclass.blockgrösse].content.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}
			
			if (Mainclass.array1[(xp + r + 25)/ Mainclass.blockgrösse]
								[(yp + 25) / Mainclass.blockgrösse].content.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}
			
			if (Mainclass.array1[(xp + r + 25)/ Mainclass.blockgrösse]
								[(yp + 50 -1) / Mainclass.blockgrösse].content.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}
		}
		
		if(rechts == true){
			xp += move -1;
		}
		
	}

	public void colisionlinks() {
		// links
		int move = 0;
		links = false;

		for (int r = 1; r < Mainclass.speed + 1; r++) {
			if (Mainclass.array1[(xp - r)/ Mainclass.blockgrösse]
								[(yp +1) / Mainclass.blockgrösse].content.begehbar() == false && links == false) {
				links = true;
				move = r;
			}
			
			if (Mainclass.array1[(xp -r)/ Mainclass.blockgrösse]
								[(yp + 25) / Mainclass.blockgrösse].content.begehbar() == false && links == false) {
				links = true;
				move = r;
			}
			
			
			if (Mainclass.array1[(xp -r)/ Mainclass.blockgrösse]
								[(yp + 50 -1) / Mainclass.blockgrösse].content.begehbar() == false && links == false) {
				links = true;
				move = r;
			}
		}
		
		if(links == true){
			xp -= move -1;
		}
	}
	public void InventarVerwenden() {
		if(Keyboard.isKeyDown(Keyboard.KEY_E) && inventarcooldown == false){
			Mainclass.InventarStatus = true;
			inventarcooldown = true;
		}
	}

	public void verschiebung() {
		Mainclass.xver = Mainclass.spielers.get(0).xp - 910;
		Mainclass.xver *= -1;
		
		Mainclass.yver = Mainclass.spielers.get(0).yp - 440;
		Mainclass.yver *= -1;

		if (Mainclass.xver >= 0) {
			Mainclass.xver = 0;
		}
		if (Mainclass.yver >= 0) {
			Mainclass.yver = 0;
		}
	}

	public void abbaue() {
		Mainclass.abbaucooldown--;
		if (Mainclass.abbaucooldown <= 0) {
			if (Mouse.isButtonDown(0) && !Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
					.equals(new Luft().getClass())) {
				Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.schläge += 1;
				Mainclass.abbaucooldown = 50;
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Dreck().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.dreckdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Bletter().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.bletterdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass().equals(new Holz().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.holzdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Stone().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.stonedig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Eisen().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.stonedig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new schneedreck().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.dreckdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Türoben().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.holzdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Türunten().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.holzdig);
				}
				if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass()
						.equals(new Feuerstelle().getClass())) {
					Mainclass.dig.playSound(Mainclass.dig.bletterdig);

				}
				Mainclass.abbaucooldown = 50;
			}
		}
	}

	public void setzen() {
		
	}

	public void spawn() {
		xp = Mainclass.spawnx;
		yp = Mainclass.spawny;
	}
}