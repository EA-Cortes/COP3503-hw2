import java.util.Scanner;


	

public class Caravan{	

	public static int n = 0; // number of cities	
	public static int m = 0; // number of possible roads
	int b; // budget
	int wc; // wagon cost

	public static class Edge implements Comparable<Edge>
	{
		int src, des, bud, cos;

		Edge(int source, int destination, int roadCost, int weightCap){
			src = source;
			des = destination;
			bud = roadCost;
			cos = weightCap;
		}

		public int compareTo(Edge o)
		{
			return Integer.compare(cos, o.cos);
		}

		public String toString()
		{
			return "(" + src + "," + des + "," + bud + "," + cos + ")";
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// First line scans n & m
		// n = number of cities
		
		n = sc.nextInt();
		m = sc.nextInt();

		// The following m lines take 4 integers, to describe the road
		// 1st int: index of one of the connected cities (starting at 1) 
		// 2nd int: index of the other connected city (starting at 1)
		// 3rd int: Cost to build the road ( n < 10,000,000)
		// 4th int: Weight capacity of the road ( n < 10,000,000)
		int k = 0;
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
				System.out.println(k);
				k++;
			}
		}

		// First line of output should contain k
		// where k is the number of possible vehicle counts that can enable the transportation of shipment in one trip

		// Second line of output should contain k distinct integers between [1, 10]

		//
	}

	public static void getEdges(){

	}
}