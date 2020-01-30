package Engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import Engine.TileComponent.Path;

public class TileComponent extends Component {

	int cost;
	
	ArrayList<TileComponent> Nei = new ArrayList<TileComponent>();
	
	public TileComponent(GameObject object, int cost) {
		super(object);
		
		this.cost = cost;
		
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void graphics() {
		
		switch(cost) {
		case 1:
			Main.grid.setColor(parent.posX, parent.posY, Color.LIGHT_GRAY);
			break;
		case 2:
			Main.grid.setColor(parent.posX, parent.posY, Color.GREEN);
			break;
		case 10:
			Main.grid.setColor(parent.posX, parent.posY, Color.yellow);
			break;
		case -1:
			Main.grid.setColor(parent.posX, parent.posY, Color.cyan);
			break;
		}
		
	}
	
	@Override
	public void logic() {
		
	}
	
	public void addNei(TileComponent N) {
		Nei.add(N);
	}
	
	@Override
	public String toString() {
		return "(" + parent.posX + "," + parent.posY + ")";
	}
	
	
	public static class AStar{
		static PriorityQueue<Path> Neis;
		static ArrayList<TileComponent> visited;
		
		public static Path findPath(TileComponent start, TileComponent destination) {
			Neis = new PriorityQueue<Path>();
			
			visited = new ArrayList<TileComponent>();
			
			Neis.add( new Path(null,
					 		   start,
					 		   Math.abs(start.parent.posX - destination.parent.posX) + Math.abs(start.parent.posY - destination.parent.posY) , 0));
			visited.add(start);
			
			Path cur ;
			while(Neis.size() != 0 && Neis.peek().cur != destination) {
				/*try {
					TimeUnit.MICROSECONDS.sleep(500000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//System.out.println(visited.size());
				//System.out.println(Neis);
				cur = Neis.poll();
				for(TileComponent i : cur.cur.Nei) {
					if(!visited.contains(i) && i.cost != -1) {
						Neis.add(new Path(cur,i,
										  i.cost+ cur.pathCost+
										  	Math.abs(i.parent.posX - destination.parent.posX) + Math.abs(i.parent.posY - destination.parent.posY),
										  i.cost + cur.pathCost)
								);
						visited.add(i);
					}
				}
			}
			if(Neis.peek()!=null)
			if(Neis.peek().cur == destination) {
				Path result = Neis.poll();
				Path.finalize(result);
				while(result.parent != null)
					result = result.parent;
				return result;
			}
			return null;
		}
	}
	
	public static class Path implements Comparable{
		public Path parent;
		public Path child = null;
		public TileComponent cur;
		int pathCost;
		int cost;
		
		Path(Path p, TileComponent t, int c, int pc){
			parent = p;
			cur = t;
			cost  = c;
			pathCost = pc;
		}
		
		public static void finalize (Path p) {
			Path tail= p;
			Path temP;
			
			while (tail.parent != null){
				tail.parent.child = tail; 
				tail = tail.parent;
			}
		}
		
		@Override
		public int compareTo(Object s) {
			
			if(s.getClass() != Path.class)
				return 0;
			Path o = (Path)s;
			return cost - o.cost;
		}
		
		
		public String toStringP() {
			if(parent != null)
				return "(" + cur.parent.posX + " , "  + cur.parent.posY + ") " + cost + " from " + parent.toStringP(); 
			return "(" + cur.parent.posX + " , "  + cur.parent.posY + ") " + cost + " start \n";
		}
		
		@Override
		public String toString() {
			if(child != null)
				return "(" + cur.parent.posX + " , "  + cur.parent.posY + ") " + cost + " from " + child.toString(); 
			return "(" + cur.parent.posX + " , "  + cur.parent.posY + ") " + cost + " start \n";
		}
		
		
	}
	
}
