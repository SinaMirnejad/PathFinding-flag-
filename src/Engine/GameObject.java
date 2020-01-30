package Engine;

import java.util.ArrayList;

public class GameObject {

	
	public String name;
	public int posX,
		   	   posY;
	
	public static int Max = 0 ,
					  Min = 0;
	
	//public ArrayList<GameObject> children = new ArrayList<GameObject>();
	public ArrayList<Component> logics = new ArrayList<Component>();
	public ArrayList<Component> graphics = new ArrayList<Component>();
	
	
	public GameObject() {
		Main.elements.add(this);
	}
	
	public void logic(int P) {
		for (Component i : logics) {
			if(i.Priority == P)
				i.logic();
		}
	}
	
	public void graphic(int P) {
		for (Component i : graphics) {
			if(i.Priority == P)
				i.graphics();
		}
		
	}
	
	public Component getComponent(Class C) {
		for(Component i : logics) {
			if(i.getClass().equals(C))
				return i;
			
		}
		for(Component i : graphics) {
			if(i.getClass().equals(C))
				return i;
			
		}
		return null;
	}
	public boolean removeComponent(Class C) {
		
		for(Component i : logics) {
			if(i.getClass().equals(C)){
				logics.remove(i);
				return true;
			}
		}
		for(Component i : graphics) {
			if(i.getClass().equals(C)) {
				logics.remove(i);
				return true;
			}
			
		}
		
		return false;
	}
	
	
	public boolean addLogicComponent(Component comp) {
		boolean duplicate =true;
		
		Class C = comp.getClass();
		
		for(Component i : logics) {
			if(i.getClass().equals(C)){
				duplicate = false;
			}
		}
		
		if(duplicate) {
			logics.add(comp);
			if(Min > comp.Priority)
				Min = comp.Priority;
			if(Max < comp.Priority)
				Max = comp.Priority;
		}
		
		return duplicate;
	}
	
	public boolean addGraphicsComponent(Component comp) {
		boolean duplicate =true;
		
		Class C = comp.getClass();
		
		for(Component i : graphics) {
			if(i.getClass().equals(C)){
				duplicate = false;
			}
		}
		
		if(duplicate) {
			graphics.add(comp);
			if(Min > comp.Priority)
				Min = comp.Priority;
			if(Max < comp.Priority)
				Max = comp.Priority;
		}
		return duplicate;
	}
	
}
