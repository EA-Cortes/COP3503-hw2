import java.util.Scanner;
import java.util.*;	
import java.util.PriorityQueue;
import java.util.Comparator;


public class Caravan{	

	public static int n; // number of cities	
	public static int m; // number of possible roads
	public static int budget; // budget
	public static int wagonCost; // wagon cost
	public static int shipWeight; // Total weight of a complete shipment

	public static int configs = 0;
	public static int wagons = 6;
	public static int nWagons[] = new int[10];

	public static class Edge implements Comparable<Edge>
	{
		int source, destination, roadCost, weightCap;

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
	// A class to represent a subset for union-find
	class subset
	{
		int parent, rank;
	}

	int V, E;
	Edge edge[];

	public static class Graph
	{
		int vertices;
		ArrayList<Edge> allEdges = new ArrayList<>();

		Graph(int vertices)
		{
			this.vertices = vertices;
		}

		public void addEdge(int source, int destination, int roadCost, int weightCap)
		{
			Edge edge = new Edge(source, destination, roadCost, weightCap);
			allEdges.add(edge);
		}

		public void kruskalMST()
		{
			PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.roadCost));

			for(int i = 0; i < allEdges.size(); i++)
			{	
				pq.add(allEdges.get(i));
			}			

			int [] parent = new int[vertices];
			makeSet(parent);
			ArrayList<Edge> mst = new ArrayList<>();
			int index = 0;

			while(index < vertices - 1)
			{
				Edge edge = pq.remove();
				int x_set = find(parent, edge.source);
				int y_set = find(parent, edge.destination);

				if(x_set == y_set || edge.weightCap * wagons < shipWeight){
					// Ignore contains both
				}else{
					mst.add(edge);						
					index++;
					union(parent, x_set, y_set);
				}
			}

		
			nWagons[configs] = wagons;
			configs++;

			System.out.println("\nMST: ");
			printGraph(mst);
		

		}

		public void makeSet(int [] parent)
		{
			for(int i = 0; i < vertices; i++)
				parent[i] = i;
		}

		// Function that finds if node already exists in tree
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

	// end of graph 		
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// First line scans n & m
		// n = number of cities
		// m = number of roads
		n = sc.nextInt();
		m = sc.nextInt();

		Graph graph = new Graph(n);



		for(int i = 0; i < m; i++)
		// The following m lines take 4 integers, to describe the road	
		{
			// 1st int: index of one of the connected cities (starting at 1) 
			int A = sc.nextInt() - 1;

			// 2nd int: index of the other connected city (starting at 1)	
			int B = sc.nextInt() - 1;

			// 3rd int: Cost to build the road ( n < 10,000,000)
			int C = sc.nextInt();
		
			// 4th int: Weight capacity of the road ( n < 10,000,000)
			int W = sc.nextInt();
			
			graph.addEdge(A, B, C, W);
		}

		budget = sc.nextInt();
		wagonCost = sc.nextInt();
		shipWeight = sc.nextInt();

		graph.kruskalMST();

		System.out.println(configs);
		for(int k = 0; k < configs; k++){
			System.out.print(nWagons[k] + " ");
		}

		System.out.println();	
		// First line of output should contain k
		// where k is the number of possible vehicle counts that can enable the transportation of shipment in one trip
		// Second line of output should contain k distinct integers between [1, 10]
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
27 1 51

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