package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Engine.Component;
import Engine.GameObject;
import Engine.InputHandler;
import Engine.Main;
import Game.GridCollider.Collision;

public class BulletCom extends Component {

	int direction,oX,oY;
	boolean death = false;
	Color back = Color.white;

	
	
	public BulletCom(GameObject object, int dir) {
		super(object);
		direction = dir;
		this.Priority = 0;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		
		if(death) {
			Main.elements.remove(parent);
			GridCollider.colliders.remove(parent.getComponent(GridCollider.class));
			Main.grid.setColor(parent.posX, parent.posY, Color.WHITE);
		}
		
		
		
		oX = parent.posX;
		oY = parent.posY;
		
		if(direction == 0) {
			parent.posX--;
		} 
		if(direction == 1) {
			parent.posX++;
		}
		if(direction == 2) {
			parent.posY++;
		}
		if(direction == 3) {
			parent.posY--;
		}
		
		
		if (parent.posY > Main.size*2-1) {
			parent.posY = Main.size*2-1;
			death =true;
		}
		if (parent.posY < 0) {
			parent.posY = 0;
			death =true;
		}
		if (parent.posX > Main.size-1) {
			parent.posX = Main.size-1;
			death =true;
		}
		if (parent.posX < 0) {
			parent.posX = 0;
			death =true;
		}

		
		//((GridCollider)parent.getComponent(GridCollider.class)).logic();
		for(Collision i : GridCollider.collisions) {
			if(i.ob1.equals(parent)) {
				//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
				death =true;
			}
		}
		
		
		
		//System.out.println("posX " + parent.posX + " posY " + parent.posY);
	}

	@Override
	public void graphics() {
		if(!death) {
			Color b = Main.grid.colors[parent.posX+Main.grid.MARGIN_SIZE][parent.posY+Main.grid.MARGIN_SIZE];
				Main.grid.setColor(parent.posX, parent.posY, Color.BLUE);
			if(oX != parent.posX || oY != parent.posY)
				Main.grid.setColor(oX, oY, back);
			back = b;
		}
		
	}
}
