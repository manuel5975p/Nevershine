package Gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import Blocks.Dreck;
import Blocks.Luft;
import Items.Dreckitem;
import Items.Frei;
import Items.Holzitem;
import Items.Holzspitzhacke;
import Items.Türitem;
import Mainpackage.Inventarfeld;
import Mainpackage.Mainclass;
import Mainpackage.Stak;

public class Inventar {
	public static void erstellen(){
		Mainclass.inventars.add(new Inventarfeld(50, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(100, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(150, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(200, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(250, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(300, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(350, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(400, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(450, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(500, 50, 40));
		Mainclass.inventars.add(new Inventarfeld(50, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(100, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(150, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(200, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(250, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(300, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(350, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(400, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(450, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(500, 110, 40));
		Mainclass.inventars.add(new Inventarfeld(50, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(100, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(150, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(200, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(250, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(300, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(350, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(400, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(450, 160, 40));
		Mainclass.inventars.add(new Inventarfeld(500, 160, 40));
		Mainclass.inventars.get(0).stak.art = new Holzspitzhacke();
		Mainclass.inventars.get(0).stak.anzahl = 1;
		Mainclass.inventars.get(1).stak.art = new Dreckitem();
		Mainclass.inventars.get(1).stak.anzahl = 10;
		
		
	}

}