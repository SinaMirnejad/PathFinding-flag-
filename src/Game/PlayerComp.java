package Game;

import java.awt.Color;
import java.awt.event.KeyEvent;

import Game.GridCollider.Collision;
import Engine.*;

public class PlayerComp extends Component {

	int oX,oY,dir = 0;
	Color back = Color.yellow,
		  b;
	
	
	
	public PlayerComp(GameObject parent){
		super(parent);
		
		parent.posX = Main.size-2;
		oX = parent.posX;
		oY = parent.posY;
		
		
	}
	
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		//System.out.println(Main.fraimes%2);
		if(Main.fraimes%2 == 0) {
			for(Collision i : GridCollider.collisions) {
				if(i.ob1.equals(parent)) {
					//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
					parent.posX = oX;
					parent.posY = oY;
				}
			}
			
			oX = parent.posX;
			oY = parent.posY;
			
			if(InputHandler.pending.contains(KeyEvent.VK_W)) {
				parent.posX--;
				dir =0;
			} 
			if(InputHandler.pending.contains(KeyEvent.VK_S)) {
				
				parent.posX++;
				dir =1;
			}
			if(InputHandler.pending.contains(KeyEvent.VK_D)) {
				parent.posY++;
				dir =2;
			}
			if(InputHandler.pending.contains(KeyEvent.VK_A)) {
				parent.posY--;
				dir =3;
			}
			
			
			if (parent.posY > Main.size*2-1)
				parent.posY = Main.size*2-1;
			if (parent.posY <= 0)
				parent.posY = 0;
			if (parent.posX > Main.size-1)
				parent.posX = Main.size-1;
			if (parent.posX <= 0)
				parent.posX = 0;
			
			((GridCollider)parent.getComponent(GridCollider.class)).logic();
			
	
			for(Collision i : GridCollider.collisions) {
				if(i.ob1.equals(parent)) {
					//System.out.println(i.ob1.name +  " collision with " + i.ob2.name);
					parent.posX = oX;
					parent.posY = oY;
				}
			}
			
			if(InputHandler.pending.contains(KeyEvent.VK_SPACE)) {
				new Bullet(dir,parent.posX,parent.posY);
				//InputHandler.pending.remove(new Integer(KeyEvent.VK_SPACE));
			}
			
			if(InputHandler.pending.contains(KeyEvent.VK_P)) {
				//Main.paused = !Main.paused ;
				InputHandler.pending.remove(new Integer(KeyEvent.VK_P));
				
			}
		}
		else {
			oX = parent.posX;
			oY = parent.posY;
		}
				
		
	}

	@Override
	public void graphics() {
		
		b = Main.grid.colors[parent.posX+Main.grid.MARGIN_SIZE][parent.posY+Main.grid.MARGIN_SIZE];

		Main.grid.setColor(parent.posX, parent.posY, Color.RED);
		
		if(oX != parent.posX || oY != parent.posY) {
			Main.grid.setColor(oX, oY, back);
			back = b;
		}
	}

	


}
