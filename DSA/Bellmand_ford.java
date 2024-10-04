import java.util.Arrays;

public class BellmanFord {

    // A class to represent a weighted edge in the graph
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Bellman-Ford algorithm to find the shortest path from source vertex
    static void bellmanFord(Edge[] edges, int vertexCount, int source) {
        // Initialize distances from the source to all vertices as infinite (Integer.MAX_VALUE)
        // and source to itself as 0
        int[] distances = new int[vertexCount];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        // Relax all edges vertexCount - 1 times
        for (int i = 1; i < vertexCount; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                // If the source vertex is reachable and we find a shorter path
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;

            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("Graph contains a negative weight cycle");
                return;
            }
        }

        // Print the distances array
        printSolution(distances, vertexCount);
    }

    // A utility function to print the distances
    static void printSolution(int[] distances, int vertexCount) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < vertexCount; i++) {
            System.out.println(i + "\t\t" + distances[i]);
        }
    }

    public static void main(String[] args) {
        int vertexCount = 5; // Number of vertices in graph
        int edgeCount = 8;   // Number of edges in graph

        Edge[] edges = new Edge[edgeCount];

        // Creating a graph with 5 vertices and 8 edges
        edges[0] = new Edge(0, 1, -1);
        edges[1] = new Edge(0, 2, 4);
        edges[2] = new Edge(1, 2, 3);
        edges[3] = new Edge(1, 3, 2);
        edges[4] = new Edge(1, 4, 2);
        edges[5] = new Edge(3, 2, 5);
        edges[6] = new Edge(3, 1, 1);
        edges[7] = new Edge(4, 3, -3);

        // Execute Bellman-Ford algorithm
        bellmanFord(edges, vertexCount, 0);
    }
}
