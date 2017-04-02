package Items;

import Blocks.Block;
import Blocks.Dreck;
import Blocks.Holz;
import Blocks.Türoben;
import Blocks.Türunten;
import Mainpackage.Mainclass;

public class Türitem extends Items{
	public Türitem(){
		
	}

	public void render(int xpos, int ypos) {
		Mainclass.Türitem.displayRect(xpos, ypos, grösse, grösse);
		
	}

	public boolean setzbar() {
		return true;
	}

	public int starke() {
		return 0;
	}

	public void setzaktion() {
		if(Mainclass.array1[Mainclass.ipos][Mainclass.bpos + 1].content.begehbar() == false){
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos].ersetzen(new Türunten());
		Mainclass.array1[Mainclass.ipos][Mainclass.bpos-1].ersetzen(new Türoben());
		}
	}
	
}
