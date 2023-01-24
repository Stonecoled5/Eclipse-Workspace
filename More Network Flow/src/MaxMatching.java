import java.util.NoSuchElementException;
import java.util.Scanner;

public class MaxMatching {

	public static void main(String[] args) {
	try {
		// read scan from scan file using scanner object
		Scanner scan;
		scan = new Scanner(System.in);
		// first number represents number of instances
		int inst = Integer.parseInt(scan.next());
		// calculate and print result for each instance
		for (int i = 0; i < inst; i++) {
			int m = Integer.parseInt(scan.next());
			int n = Integer.parseInt(scan.next());
			int q = Integer.parseInt(scan.next());
			// create an array to represent 2D graph
			// we will create boolean array true value represents edge present
			// between two nodes from node set A and B
			boolean graph[][] = new boolean[m][n];
			// initialize graph with no edges
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < n; k++) {
					graph[j][k] = false;
				}
			}

			// add edges to graph
			for (int j = 0; j < q; j++) {
				// we will represent edge in 2 nodes
				// left node with a and right node with b
				int a = Integer.parseInt(scan.next());
				int b = Integer.parseInt(scan.next());
				// mark edge on graph
				graph[a - 1][b - 1] = true; // remove 1 as array index start from 0
			}
			// calculate maximum matching
			int maxMatch = maximumMatching(graph, m, n);
			String perfectMatch = "N";
			// perfect match exist if all node from set A have one edge to set B and
			// similarly
			// all node from Set B have one edge to set A
			if ((m == n) && (m == maxMatch)) {
				perfectMatch = "Y";
			}
			// output result for current instance
			System.out.println(String.valueOf(maxMatch) + " " + perfectMatch);
		}
		// close the scanner
		scan.close();
	}
	catch(NoSuchElementException e) {
		return;
	}
	}

	/*
	 * Returns maximum number of matching from A to B
	 */
	private static int maximumMatching(boolean[][] graph, int M, int N) {
		// we will create an array to keep track of matching nodes from set A to set B
		// value of array is matching node m in A to node n in B
		// negative value indicates no matching

		int match[] = new int[N];
		// Initially all nodes from B are not matched
		for (int i = 0; i < N; i++) {
			match[i] = -1;
		}
		// Count of matching nodes from set A to set B
		int result = 0;
		for (int u = 0; u < M; u++) {
			// Mark all positions as not seen
			// for next node number.
			boolean visited[] = new boolean[N];
			for (int i = 0; i < N; ++i)
				visited[i] = false;
			// check if the node 'u' have a match
			if (hasMatch(graph, u, N, visited, match)) {
				result++;
			}
		}
		return result;
	}

	/*
	 * Helper recursive function that return true if match found for node u in node set B
	 */
	private static boolean hasMatch(boolean[][] graph, int u, int N, boolean[] visited, int[] match) {
		// check every node from set B
		for (int v = 0; v < N; v++) {
			// if there is edge and it's not visited then mark it as visited
			if (graph[u][v] && !visited[v]) {
				visited[v] = true;
				// make recursive call to find next possible match
				if (match[v] < 0 || hasMatch(graph, match[v], N, visited, match)) {
					match[v] = u;
					return true;
				}
			}
		}
		return false;
	}
}
