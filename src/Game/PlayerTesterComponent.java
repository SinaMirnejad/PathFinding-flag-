package Game;

import java.awt.Color;
import java.util.Random;

import Engine.Component;
import Engine.GameObject;
import Engine.Main;

public class PlayerTesterComponent extends Component{


	public PlayerTesterComponent(GameObject object) {
		super(object);
		// TODO Auto-generated constructor stub
	}
	
	String name;
	
	@Override
	public void graphics() {
		Main.grid.setColor(parent.posX, parent.posY, Color.ORANGE);
	}

}
