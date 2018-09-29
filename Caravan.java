import java.util.Scanner;
import java.util.*;	

public class Caravan{	
	public static int n; // number of cities	
	public static int m; // number of possible roads
	public static int budget; // budget
	public static int wagonCost; // wagon cost
	public static int shipWeight; // Total weight of a complete shipment
	public static int configs = 0;	// Accounts for the total number of working configurations
	public static int wagons = 10;	// Greedy assumption of the number of wagons, that we're right
	public static int nWagons[] = new int[10]; // Working configurations will be stored here

	public static class Edge implements Comparable<Edge>
	{
		int source, destination, roadCost, weightCap;
		// Edge Constructor
		Edge(int source, int destination, int roadCost, int weightCap){
			this.source = source;
			this.destination = destination;
			this.roadCost = roadCost;
			this.weightCap = weightCap;
		}

		public int compareTo(Edge o)
		{
			return Integer.compare(roadCost, o.roadCost);
		}

		public String toString()
		{
			return "(" + this.source + ", " + this.destination + ", " + this.roadCost + ", " + this.weightCap + ")";
		}
	}

	public static class Graph
	{
		int vertices;
		ArrayList<Edge> allEdges = new ArrayList<>();

		Graph(int vertices)
		{
			this.vertices = vertices;
		}

		// Auxilary function to add edges to our graph
		public void addEdge(int source, int destination, int roadCost, int weightCap)
		{
			Edge edge = new Edge(source, destination, roadCost, weightCap);
			allEdges.add(edge);
		}

		public void kruskalMST()
		{
			// Step 1: Sort edges by roadCost
			PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.roadCost));

			for(int i = 0; i < allEdges.size(); i++)
			{	
				pq.add(allEdges.get(i));
			}			

			int [] parent = new int[vertices];
			makeSet(parent);
			ArrayList<Edge> mst = new ArrayList<>();
			int index = 0, tempCost = 0;

			// Step 2: Pick the lowest cost edges to add to our result
			while(index < vertices - 1)
			{
				Edge edge = pq.remove();
				int x_set = find(parent, edge.source);
				int y_set = find(parent, edge.destination);

				// If creates a cycle, or a road with n wagons can't support the total weight ignore it
				if(x_set == y_set || edge.weightCap * wagons < shipWeight){
					// Rawr xD
				}else{
					// These are valid edges for our MST
					mst.add(edge);
					tempCost += edge.roadCost;
					index++;
					union(parent, x_set, y_set);
				}
				// If there are no edges left, break out of the while loop
				if(pq.size() < 1)
					break;
			}

			tempCost += wagonCost * wagons;			
			if(tempCost <= budget && mst.size() == n -1)
			{
				/* ----------- Output working MSTs -----------
				 System.out.println("\nWagons: " + wagons + " Cost: " + tempCost);
				 System.out.println("MST: ");
				 printGraph(mst);
				 */ 
				nWagons[configs] = wagons;
				configs++;					
			}
				wagons--;			
		}

		// Auxilary function to create subset 
		public void makeSet(int [] parent)
		{
			for(int i = 0; i < vertices; i++)
				parent[i] = i;
		}

		// Auxliary function that finds if node already exists in tree
		public int find(int [] parent, int vertex)
		{
			if(parent[vertex] != vertex)
				return find(parent, parent[vertex]);
			return vertex;
		}

		// Function that that does the union of two sets
		void union(int [] parent, int x, int y)
		{
			int x_set_parent = find(parent, x);
			int y_set_parent = find(parent, y);
			parent[y_set_parent] = x_set_parent;
		}
	
		// Output function
		public void printGraph(ArrayList<Edge> edgeList)
		{
			for(int i = 0; i < edgeList.size(); i++)
			{
				Edge edge = edgeList.get(i);
				System.out.println(edge);
			}
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); 				// number of cities
		m = sc.nextInt(); 				// number of possible roads
		Graph graph = new Graph(n);		// Init our graph

		for(int i = 0; i < m; i++)
		{
			// The following m lines scan 4 integers, to describe the road	
			int origin = sc.nextInt() - 1;
			int destination = sc.nextInt() - 1;
			int roadCost = sc.nextInt();
			int weightLimit = sc.nextInt();
			
			//	adds those 4 integers into our list of Edges
			graph.addEdge(origin, destination, roadCost, weightLimit);
		}

		budget = sc.nextInt();			// Total Budget
		wagonCost = sc.nextInt();		// Cost of each wagon
		shipWeight = sc.nextInt();		// Total weight

		// We're assuming we can buy 10 or less wagons. 
		// We're going to iterate through the cases
		for(int k = 10; k > 0; k--)
		{
			graph.kruskalMST();
		}
		
		// Output the number of working configurations as well as how many wagons there are in each
		System.out.println(configs);
		for(int k = configs - 1; k >= 0; k--){
			System.out.print(nWagons[k] + " ");
		}

		System.out.println();	
	}
}

/*

--------------------- Sample Input ---------------------
4 5
1 3 3 6
1 2 20 100
2 3 5 30
3 4 10 10
2 4 1 5
71 6 49

Case 1:
27 1 51
1 
9

Case 2:
41 1 55
2
6 10

Case 3:
71 6 49
3
5 6 10

*/