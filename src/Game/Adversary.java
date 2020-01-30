package Game;

import java.awt.Color;
import java.util.Random;

import Engine.GameObject;
import Engine.Main;
import Engine.TileComponent;
import Game.GridCollider.Collision;

public class Adversary extends Obstacles{

	boolean death = false;
	TileComponent.Path myPath= null;
	Random rand  = new Random();
	
	public Adversary(GameObject object) {
		super(object);
		parent.posX = 0;
		parent.posY = Main.size *2 -1;
	}
	
	@Override
	public void logic() {
		if (myPath == null) {
			
			myPath = TileComponent.AStar.findPath(Main.Mesh[parent.posX][parent.posY],
												  Main.Mesh[rand.nextInt(Main.size)][rand.nextInt(Main.size) + rand.nextInt(Main.size)]);
			System.out.println(myPath);
		}
		else {
		parent.posX = myPath.cur.parent.posX;
		parent.posY = myPath.cur.parent.posY;
		myPath = myPath.child;
		}
		for(Collision i : GridCollider.collisions) {
			if(i.ob1.equals(parent) && i.ob2.name.equals("Bullet")) {
				death =true;
			}
		}
		
		if(death) {
			Main.elements.remove(parent);
			GridCollider.colliders.remove(parent.getComponent(GridCollider.class));
			Main.grid.setColor(parent.posX, parent.posY, Color.WHITE);
		}
	}
	
	@Override
	public void graphics() {
		Main.grid.setColor(parent.posX, parent.posY, Color.GRAY);
	}
}
