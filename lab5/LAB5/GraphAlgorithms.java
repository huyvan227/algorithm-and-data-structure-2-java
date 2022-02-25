
import java.util.Set;
import java.util.HashSet;

/**
 * A collection of graph algorithms.
 */
public class GraphAlgorithms {

	/**
	 * Performs depth-first search of the unknown portion of Graph g starting at
	 * Vertex u.
	 *
	 * @param g
	 *            Graph instance
	 * @param u
	 *            Vertex of graph g that will be the source of the search
	 * @param known
	 *            is a set of previously discovered vertices
	 * @param forest
	 *            is a map from nonroot vertex to its discovery edge in DFS
	 *            forest
	 *
	 *            As an outcome, this method adds newly discovered vertices
	 *            (including u) to the known set, and adds discovery graph edges
	 *            to the forest.
	 */
	public static <V, E> void DFS(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
		//write your code here
	}

	/**
	 * Performs DFS for the entire graph and returns the DFS forest as a map.
	 *
	 * @return map such that each nonroot vertex v is mapped to its discovery
	 *         edge (vertices that are roots of a DFS trees in the forest are
	 *         not included in the map).
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> DFSComplete(Graph<V, E> g) {
		Set<Vertex<V>> known = new HashSet<>();
		Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
		for (Vertex<V> u : g.vertices())
			if (!known.contains(u))
				DFS(g, u, known, forest); // (re)start the DFS process at u
		return forest;
	}

	/**
	 * Performs breadth-first search of the undiscovered portion of Graph g
	 * starting at Vertex s.
	 *
	 * @param g
	 *            Graph instance
	 * @param s
	 *            Vertex of graph g that will be the source of the search
	 * @param known
	 *            is a set of previously discovered vertices
	 * @param forest
	 *            is a map from nonroot vertex to its discovery edge in DFS
	 *            forest
	 *
	 *            As an outcome, this method adds newly discovered vertices
	 *            (including s) to the known set, and adds discovery graph edges
	 *            to the forest.
	 */
	public static <V, E> void BFS(Graph<V, E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
		//write your code here
	}

	/**
	 * Performs BFS for the entire graph and returns the BFS forest as a map.
	 *
	 * @return map such that each nonroot vertex v is mapped to its discovery
	 *         edge (vertices that are roots of a BFS trees in the forest are
	 *         not included in the map).
	 */
	public static <V, E> Map<Vertex<V>, Edge<E>> BFSComplete(Graph<V, E> g) {
		Map<Vertex<V>, Edge<E>> forest = new ProbeHashMap<>();
		Set<Vertex<V>> known = new HashSet<>();
		for (Vertex<V> u : g.vertices())
			if (!known.contains(u))
				BFS(g, u, known, forest);
		return forest;
	}
}
