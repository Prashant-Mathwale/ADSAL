import java.util.*;

public class DFSCities {

    // Graph represented using adjacency list
    HashMap<String, ArrayList<String>> graph;

    // Set to keep track of visited cities
    HashSet<String> visited;

    // Constructor
    DFSCities() {
        graph = new HashMap<>();
        visited = new HashSet<>();
    }

    // Function to add an edge between two cities (undirected graph)
    void addEdge(String u, String v) {

        // Validation: city names should not be null or empty
        if (u == null || v == null || u.trim().isEmpty() || v.trim().isEmpty()) {
            System.out.println("Invalid city name. Skipping this edge.");
            return;
        }

        // Convert to consistent format (optional)
        u = u.trim();
        v = v.trim();

        // Create list if city not present
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());

        // Add bidirectional edge
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    // DFS traversal function
    void dfs(String city) {

        // Validation: if city doesn't exist in graph
        if (!graph.containsKey(city)) {
            System.out.println("\nError: Starting city not found in graph.");
            return;
        }

        // Mark city as visited
        visited.add(city);

        // Print current city
        System.out.print(city + " ");

        // Traverse all neighbors
        for (String neighbor : graph.get(city)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DFSCities g = new DFSCities();

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
            System.out.print("DFS Traversal: ");
            g.dfs(start);
        }

        sc.close();
    }
}