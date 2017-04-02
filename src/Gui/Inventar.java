package Gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Blocks.Luft;
import Items.Frei;
import Items.Holzitem;
import Items.Türitem;
import Mainpackage.Inventarfelder;
import Mainpackage.Mainclass;
import Mainpackage.Stak;

public class Inventar {

	public static void render() {
		if (Mainclass.Feuerinventar == true) {
			Feuerinventar.render();
		}
		for (int c = 0; c < Mainclass.xinventar; c++) {
			for (int d = 0; d < Mainclass.yinventar; d++) {
				Mainclass.inventarfeld[c][d].inhalt(c * 100 + 63, d * 100 + 63);
				GL11.glColor4f(1, 1, 1, 1);
				if (Mainclass.inventarfeld[c][d].inventarstak.anzahl <= 0) {
					Mainclass.inventarfeld[c][d].ersetzen(new Frei());
					Mainclass.inventarfeld[c][d].inventarstak.anzahl = 0;
				}
				if (Mainclass.inventarfeld[c][d].inventarstak.anzahl > 0) {
					Mainclass.superschrift.displayText(c * 100 + 80, d * 100 + 80, "" + Mainclass.inventarfeld[c][d].inventarstak.anzahl, 15);
				}
			}
		}
		for (int c = 0; c < 10; c++) {
			for (int d = 0; d < 4; d++) {
				GL11.glColor4f(1, 1, 1, 1);
				Mainclass.Itemframe.displayRect(c * 100 + 50, d * 100 + 50, 50, 50);
			}
		}
		if (Mainclass.Mousestak.anzahl <= 0) {
			Mainclass.Mousestak.art = new Frei();
			Mainclass.Mousestak.anzahl = 0;
			Mainclass.einmal = 0;
		}
		Mouserender();
		Inventarfelder.staknehmen();
		Inventarfelder.staksetzen();
		Inventarfelder.InvMaus();
		if (Mainclass.verwendung > 0) {
			Mainclass.verwendung--;
		}
	}

	public static void render2() {
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			Mainclass.xauswahl = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
			Mainclass.xauswahl = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_3)) {
			Mainclass.xauswahl = 2;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_4)) {
			Mainclass.xauswahl = 3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_5)) {
			Mainclass.xauswahl = 4;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_6)) {
			Mainclass.xauswahl = 5;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_7)) {
			Mainclass.xauswahl = 6;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_8)) {
			Mainclass.xauswahl = 7;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_9)) {
			Mainclass.xauswahl = 8;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
			Mainclass.xauswahl = 9;
		}
		for (int c = 0; c < 10; c++) {
			Mainclass.ausgewählt.displayRect(Mainclass.xauswahl * 100 + 50, 50, 50, 50);
			Mainclass.inventarfeld[c][0].inhalt(c * 100 + 63, 63);
			GL11.glColor4f(1, 1, 1, 1);
			Mainclass.Itemframe.displayRect(c * 100 + 50, 50, 50, 50);
			if (Mainclass.inventarfeld[c][0].inventarstak.anzahl > 0) {
				Mainclass.superschrift.displayText(c * 100 + 80, 80, "" + Mainclass.inventarfeld[c][0].inventarstak.anzahl, 15);
			}
		}
		for (int c = 0; c < 10; c++) {
			for (int d = 0; d < Mainclass.yinventar; d++) {
				if (Mainclass.inventarfeld[c][d].inventarstak.anzahl <= 0) {
					Mainclass.inventarfeld[c][d].ersetzen(new Frei());
					Mainclass.inventarfeld[c][d].inventarstak.anzahl = 0;
				}
			}
		}
	}

	public static void einsortieren() {
		int i = 0, b = 0, c = 0;
		while (c == 0) {
			if (i == 9 && b < 4) {
				b++;
				i = 0;
			}
			if (Mainclass.inventarfeld[i][b].inventarstak.art.getClass().equals(new Frei().getClass())) {
				Mainclass.inventarfeld[i][b].ersetzen(Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.drop());
				Mainclass.inventarfeld[i][b].inventarstak.anzahl += 1;
				c = 1;
			} else if (Mainclass.inventarfeld[i][b].inventarstak.art.getClass().equals(Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.drop().getClass())) {
				Mainclass.inventarfeld[i][b].ersetzen(Mainclass.array1[Mainclass.ipos][Mainclass.bpos].content.drop());
				Mainclass.inventarfeld[i][b].inventarstak.anzahl += 1;
				c = 1;
			}
			if (!Mainclass.inventarfeld[i][b].inventarstak.art.getClass().equals(new Frei().getClass())) {
				i++;
			}
		}
	}

	public static void Mouserender() {
		Mainclass.Mousestak.art.render(Mouse.getX(), Display.getHeight() - Mouse.getY());
		Mainclass.superschrift.displayText(Mouse.getX() + 20, Display.getHeight() - Mouse.getY() + 17, "" + Mainclass.Mousestak.anzahl, 15);
		if (Mainclass.Mousestak.anzahl == 0) {
			Mainclass.Mousestak.art = new Frei();
		}
	}
}
