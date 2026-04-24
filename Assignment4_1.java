import java.util.*;

public class BFSCities {

    // Graph represented using adjacency list
    HashMap<String, ArrayList<String>> graph;

    // Constructor
    BFSCities() {
        graph = new HashMap<>();
    }

    // Function to add an edge (undirected graph)
    void addEdge(String u, String v) {

        // Validation: city names should not be null or empty
        if (u == null || v == null || u.trim().isEmpty() || v.trim().isEmpty()) {
            System.out.println("Invalid city name. Skipping this edge.");
            return;
        }

        u = u.trim();
        v = v.trim();

        // Create adjacency list if not present
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        // Add bidirectional edge
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    // BFS traversal function
    void bfs(String start) {

        // Validation: starting city must exist
        if (!graph.containsKey(start)) {
            System.out.println("\nError: Starting city not found in graph.");
            return;
        }

        // Set to track visited cities
        HashSet<String> visited = new HashSet<>();

        // Queue for BFS
        Queue<String> q = new LinkedList<>();

        // Start BFS
        visited.add(start);
        q.add(start);

        while (!q.isEmpty()) {
            String city = q.poll();

            // Print current city
            System.out.print(city + " ");

            // Visit all neighbors
            for (String neighbor : graph.get(city)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    q.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BFSCities g = new BFSCities();

        int e = 0;

        // Input validation for number of edges
        while (true) {
            System.out.print("Enter number of edges: ");
            if (sc.hasNextInt()) {
                e = sc.nextInt();

                if (e < 0) {
                    System.out.println("Number of edges cannot be negative.");
                    continue;
                }
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // clear invalid input
            }
        }

        sc.nextLine(); // consume leftover newline

        // Input edges
        for (int i = 0; i < e; i++) {
            System.out.print("Enter edge (city1 city2): ");

            if (!sc.hasNext()) {
                System.out.println("Invalid input. Try again.");
                i--;
                continue;
            }

            String u = sc.next();

            if (!sc.hasNext()) {
                System.out.println("Invalid input. Try again.");
                i--;
                continue;
            }

            String v = sc.next();

            g.addEdge(u, v);
        }

        // Input starting city
        System.out.print("Enter starting city: ");
        String start = sc.next();

        // Check if graph is empty
        if (g.graph.isEmpty()) {
            System.out.println("Graph is empty. No traversal possible.");
        } else {
            System.out.print("BFS Traversal: ");
            g.bfs(start);
        }

        sc.close();
    }
}