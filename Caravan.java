import java.util.Scanner;
import java.util.*;

	

public class Caravan{	

	public static int n; // number of cities	
	public static int m; // number of possible roads
	public static int budget; // budget
	public static int wagonCost; // wagon cost
	public static int shipWeight; // Total weight of a complete shipment

	

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// First line scans n & m
		// n = number of cities
		
		n = sc.nextInt();
		m = sc.nextInt();

		


		ArrayList<Edge>[] adjList = new ArrayList[n];
		for(int i = 0; i < n; i++)
		{
			adjList[i] = new ArrayList<Edge>();
		}

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

			Edge e1 = new Edge( A, B, C, W);

			adjList[A].add(e1);
		}

		budget = sc.nextInt();
		wagonCost = sc.nextInt();
		shipWeight = sc.nextInt();

		System.out.println("\n[Un]Sorted Edges:");
		for(ArrayList<Edge> al : adjList)
		{
			for(Edge e : al)
				System.out.println(e);
		}
		// First line of output should contain k
		// where k is the number of possible vehicle counts that can enable the transportation of shipment in one trip

		// Second line of output should contain k distinct integers between [1, 10]

		//
	}

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

	public static void getEdges()
	{

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

--------------------- Sample Output ---------------------
1
9
*/

