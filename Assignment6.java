import java.util.*;

class Edge {
    int vertex, weight;

    Edge(int v, int w) {
        vertex = v;
        weight = w;
    }
}

public class Dijkstras {

    static void dijkstra(List<List<Edge>> graph, int V, int source, String[] cities) {

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        pq.add(new Edge(source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge neighbor : graph.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }

        // Output using city names
        System.out.println("\nShortest distances from " + cities[source] + ":");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println(cities[source] + " -> " + cities[i] + " = INF");
            else
                System.out.println(cities[source] + " -> " + cities[i] + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of cities
        System.out.print("Enter number of cities: ");
        int V = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] cities = new String[V];

        // Input city names
        System.out.println("Enter city names:");
        for (int i = 0; i < V; i++) {
            cities[i] = sc.nextLine();
        }

        // Map city name → index
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < V; i++) {
            map.put(cities[i], i);
        }

        // Graph creation
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // Number of edges
        System.out.print("Enter number of roads: ");
        int E = sc.nextInt();

        System.out.println("Enter roads (source destination distance):");

        for (int i = 0; i < E; i++) {
            String srcName = sc.next();
            String destName = sc.next();
            int weight = sc.nextInt();

            int u = map.get(srcName);
            int v = map.get(destName);

            // Undirected graph
            graph.get(u).add(new Edge(v, weight));
            graph.get(v).add(new Edge(u, weight));
        }

        // Source city
        System.out.print("Enter source city: ");
        String sourceName = sc.next();

        int source = map.get(sourceName);

        dijkstra(graph, V, source, cities);

        sc.close();
    }
}