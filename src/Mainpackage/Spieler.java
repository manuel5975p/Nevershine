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
import Items.Frei;

public class Spieler {
	int yp, grösse, xp, gesch, px, py, leben, maxleben, usecooldown2, jumpp, keyspace = 0, inventarcooldown,
			abbaucooldown, setzcooldown;
	float  falls = 0;
	boolean oben, unten, rechts, links, jump = false;

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
		//Leben
		GL11.glColor3f(1, 0.2f, 0.1f);
		Mainclass.healthbar(1550, 30, 300, 15, leben, maxleben);
		
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
		if (xp < 0) {
			xp = 0;
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
		if (oben == true) {
			jumpp = 0;
			jump = false;
		}
	}

	public void fall() {
		if (jump == false) {
			colisionUnten();

			if (falls < 16) {
				falls += 1;
			}
		}
		if (jump == false && unten == false) {
			Mainclass.spielers.get(0).yp += falls;
		}
	}

	public void colisionOben() {
		// oben
		int move = 0;
		oben = false;

		for (int r = 1; r < falls + 1; r++) {
			if (Mainclass.array1[(xp + 1) / Mainclass.blockgrösse][(yp - r) / Mainclass.blockgrösse].content
					.begehbar() == false && oben == false) {
				oben = true;
				move = r;
			}
			if (Mainclass.array1[(xp + 25 - 1) / Mainclass.blockgrösse][(yp - r) / Mainclass.blockgrösse].content
					.begehbar() == false && oben == false) {
				oben = true;
				move = r;
			}
		}

		if (oben == true) {
			yp -= move - 1;
		}
	}

	public void colisionUnten() {
		// unten
		int move = 0;
		unten = false;

		for (int r = 1; r < falls + 1; r++) {
			if (Mainclass.array1[(xp + 1) / Mainclass.blockgrösse][(yp + 50 + r - 1) / Mainclass.blockgrösse].content
					.begehbar() == false && unten == false) {
				unten = true;
				move = r;
				
				if(falls > 10) {
					takedamage(falls * 4);
				}
				
				falls = 0;
			}
			if (Mainclass.array1[(xp + 25 - 1) / Mainclass.blockgrösse][(yp + 50 + r - 1)
					/ Mainclass.blockgrösse].content.begehbar() == false && unten == false) {
				unten = true;
				move = r;
				if(falls > 15) {
					takedamage(falls * 2);
					System.out.println(falls*2);
				}
				falls = 0;
			}
		}

		if (unten == true) {
			yp += move - 1;
		}
		if (keyspace > 1) {
			keyspace = 0;
		}
	}

	public void colisionRechts() {
		// rechts
		int move = 0;
		rechts = false;

		for (int r = 1; r < Mainclass.speed + 1; r++) {
			if (Mainclass.array1[(xp + r + 25) / Mainclass.blockgrösse][(yp + 1) / Mainclass.blockgrösse].content
					.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}

			if (Mainclass.array1[(xp + r + 25) / Mainclass.blockgrösse][(yp + 25) / Mainclass.blockgrösse].content
					.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}

			if (Mainclass.array1[(xp + r + 25) / Mainclass.blockgrösse][(yp + 50 - 1) / Mainclass.blockgrösse].content
					.begehbar() == false && rechts == false) {
				rechts = true;
				move = r;
			}
		}

		if (rechts == true) {
			xp += move - 1;
		}

	}

	public void colisionlinks() {
		// links
		int move = 0;
		links = false;

		for (int r = 1; r < Mainclass.speed + 1; r++) {
			if (Mainclass.array1[(xp - r) / Mainclass.blockgrösse][(yp + 1) / Mainclass.blockgrösse].content
					.begehbar() == false && links == false) {
				links = true;
				move = r;
			}

			if (Mainclass.array1[(xp - r) / Mainclass.blockgrösse][(yp + 25) / Mainclass.blockgrösse].content
					.begehbar() == false && links == false) {
				links = true;
				move = r;
			}

			if (Mainclass.array1[(xp - r) / Mainclass.blockgrösse][(yp + 50 - 1) / Mainclass.blockgrösse].content
					.begehbar() == false && links == false) {
				links = true;
				move = r;
			}
		}

		if (links == true) {
			xp -= move - 1;
		}
	}

	public void InventarVerwenden() {
		inventarcooldown += 1;

		if (Keyboard.isKeyDown(Keyboard.KEY_E) && inventarcooldown >= 15 && Mainclass.InventarStatus == false) {
			Mainclass.InventarStatus = true;
			inventarcooldown = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E) && inventarcooldown >= 15 && Mainclass.InventarStatus == true) {

			Mainclass.InventarStatus = false;
			inventarcooldown = 0;
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
		int t = 0;
		abbaucooldown++;
		if (abbaucooldown > 30) {
			abbaucooldown = 30;
		}
		if (Mouse.isButtonDown(0) == true && abbaucooldown == 30 && Mainclass.inventars.get(Mainclass.ausgewahlt).stak.art.starke() > 0) {
			Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content
					.abbau(Mainclass.inventars.get(Mainclass.ausgewahlt).stak.art.starke());
			Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.sound();

			// isortiere
			if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getharte() <= 0) {
				for (int i = 0; i < Mainclass.inventars.size(); i++) {
					if (Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.drop().getClass()
							.equals(Mainclass.inventars.get(i).stak.art.getClass())
							&& Mainclass.inventars.get(i).stak.anzahl < 99 && t == 0) {
						Mainclass.inventars.get(i).stak.anzahl += 1;
						t = 1;
					}
				}
			for (int i = 0; i < Mainclass.inventars.size(); i++) {
				if (Mainclass.inventars.get(i).stak.art.getClass().equals(Frei.class) && t == 0) {
					Mainclass.inventars.get(i).stak.art = Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.drop();
					Mainclass.inventars.get(i).stak.anzahl += 1;
					t = 1;
				}
			}
			}
			abbaucooldown = 0;
		}
	}

	public void setzen() {
		if (setzcooldown >= 10) {
			if (Mouse.isButtonDown(1) == true && Mainclass.InventarStatus == false) {
				if (Mainclass.inventars.get(Mainclass.ausgewahlt).stak.anzahl > 0
						&& Mainclass.inventars.get(Mainclass.ausgewahlt).stak.art.setzbar() == true
						&& Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.getClass().equals(Luft.class)) {
					if (!Mainclass.array1[(xp + 1) / Mainclass.blockgrösse][yp / Mainclass.blockgrösse]
							.equals(Mainclass.array1[Mainclass.ipos][Mainclass.bpos])
							&& !Mainclass.array1[(xp + 1) / Mainclass.blockgrösse][(yp + 25) / Mainclass.blockgrösse]
									.equals(Mainclass.array1[Mainclass.ipos][Mainclass.bpos])
							&& !Mainclass.array1[(xp + 24) / Mainclass.blockgrösse][yp / Mainclass.blockgrösse]
									.equals(Mainclass.array1[Mainclass.ipos][Mainclass.bpos])
							&& !Mainclass.array1[(xp + 24) / Mainclass.blockgrösse][(yp + 25) / Mainclass.blockgrösse]
									.equals(Mainclass.array1[Mainclass.ipos][Mainclass.bpos])) {
						Mainclass.inventars.get(Mainclass.ausgewahlt).stak.art.setzaktion();
						Mainclass.inventars.get(Mainclass.ausgewahlt).stak.anzahl--;
					}
				}
			}
			setzcooldown = 0;
		}
		if (Mouse.isButtonDown(1) == false) {
			setzcooldown = 10;
		}
		setzcooldown++;
	}

	public void spawn() {
		xp = Mainclass.spawnx;
		yp = Mainclass.spawny;
		leben = maxleben;
	}
	
	public void takedamage(float damage) {
		leben -= damage;
		if(leben <= 0) {
			spawn();
		}
	}
}