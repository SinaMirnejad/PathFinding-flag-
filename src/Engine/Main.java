package Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Game.*;

public class Main {

	
	public static MyGrid grid;
	public static int size = 10;
	public static InputHandler IH;
	public static ArrayList<GameObject> elements = new ArrayList<GameObject>();
	public static int fraimes = 0;
	public static TileComponent[][] Mesh = new TileComponent[size][size*2];
	
	
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		Scanner reader = new Scanner(new File("Assets//Map1"));
		
		grid  = new MyGrid(size);
		IH = new InputHandler(grid);
		
		
		// Add Comp here! -----------------------
		//player.addGraphicsComponent(new PlayerTesterComponent(player));
		
		Component tempC;
		
		//------------------------------------------
		
		{
				
			GameObject temp;
			int input;
			for(int i = 0 ; i < size; i++) {
				for(int j = 0 ; j < 2*size; j++) {
					temp = new GameObject();
					temp.posX = i;
					temp.posY = j;
					input = reader.nextInt();
					if(input == -1) {
						temp.addLogicComponent(new GridCollider(temp));
						
					}
					tempC = new TileComponent(temp,input);
					temp.addGraphicsComponent(tempC);
					temp.name = "tile " + i + " " + j;
					Mesh[i][j] =(TileComponent) tempC;
				}
				
				
			}
			
			for(int i = 0 ; i < size; i++) {
				for(int j = 0 ; j < 2*size; j++) {
					if(i > 0)
						Mesh[i][j].addNei(Mesh[i-1][j]);
					if(j > 0)
						Mesh[i][j].addNei(Mesh[i][j-1]);
					if(i < size-1)
						Mesh[i][j].addNei(Mesh[i+1][j]);
					if(j < size-1)
						Mesh[i][j].addNei(Mesh[i][j+1]);
				}
			}
			TileComponent.Path P = TileComponent.AStar.findPath(Mesh[2][2], Mesh[8][8]);
			System.out.println(P);
		}
		
		GameObject Advosary = new GameObject();
		tempC = new Adversary(Advosary);
		Advosary.addLogicComponent(tempC);
		Advosary.addGraphicsComponent(tempC);
		Advosary.addLogicComponent(new GridCollider(Advosary));
		Advosary.name = "Advosary";
		

		GameObject player = new GameObject();		
		tempC = new PlayerComp(player);
		player.addLogicComponent(tempC);
		player.addGraphicsComponent(tempC);
		player.addLogicComponent(new GridCollider(player));
		player.name = "player";
		tempC.Priority = 0;
		
		
		
		while(true) {
			fraimes = (fraimes + 1) % 2;
			ArrayList<GameObject> temp = new ArrayList<GameObject>(elements);
			
			for(int i = GameObject.Min; i <= GameObject.Max; i++) {
				for(GameObject j: temp)
					j.logic(i);
			}
		
			
			for(int i = GameObject.Min; i <= GameObject.Max; i++) {
				for(GameObject j: temp)
					j.graphic(i);;
			}
			
			IH.Reset();
			GridCollider.reset();
			grid.repaint();
			TimeUnit.MICROSECONDS.sleep(50000);
		}
		
	}
	
	
}
