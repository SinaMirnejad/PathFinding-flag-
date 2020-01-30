package Game;

import Engine.*;

public class Bullet extends GameObject{

	public int count =0;
	
	public Bullet(int dir, int iniX, int iniY) {
		super();
		posX = iniX;
		posY = iniY;
		if(dir == 0) {
			posX = iniX-1;
			posY = iniY;
		}
		if(dir == 1) {
			posX = iniX+1;
			posY = iniY;
		}
		if(dir == 2) {
			posX = iniX;
			posY = iniY +1;
		}
		if(dir == 3) {
			posX = iniX;
			posY = iniY -1;
		}
		Component tempC = new BulletCom(this, dir);
		this.addLogicComponent(tempC);
		this.addGraphicsComponent(tempC);
		this.addLogicComponent(new GridCollider(this));
		this.name = "Bullet";
	}
}