import java.util.*;

class edge {
    int src, dest, weight;

    edge(int s, int d, int w) {
        src = s;
        dest = d;
        weight = w;
    }
}

public class Assignment5_2 {

    int find(int parent[], int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]); // path compression
        return parent[i];
    }

    void union(int parent[], int rank[], int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    void kruskalMST(List<edge> edges, int V, String[] locations) {

        // Sort edges by weight
        edges.sort(Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[V];
        int[] rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int count = 0;
        int totalCost = 0;

        System.out.println("\nSelected Roads (MST):");
        System.out.println("Path\t\tDistance");

        for (edge e : edges) {
            if (count == V - 1) break;

            int x = find(parent, e.src);
            int y = find(parent, e.dest);

            if (x != y) {
                System.out.println(locations[e.src] + " -> " +
                        locations[e.dest] + "\t" + e.weight);

                totalCost += e.weight;
                union(parent, rank, x, y);
                count++;
            }
        }

        System.out.println("Total Minimum Distance = " + totalCost);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of locations
        System.out.print("Enter number of locations: ");
        int V = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] locations = new String[V];

        // Input location names
        System.out.println("Enter location names:");
        for (int i = 0; i < V; i++) {
            locations[i] = sc.nextLine();
        }

        // Create mapping: name → index
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < V; i++) {
            map.put(locations[i], i);
        }

        // Number of roads
        System.out.print("Enter number of roads: ");
        int E = sc.nextInt();

        List<edge> edges = new ArrayList<>();

        System.out.println("Enter roads (source destination distance):");

        for (int i = 0; i < E; i++) {
            String srcName = sc.next();   // city name
            String destName = sc.next();  // city name
            int weight = sc.nextInt();

            int src = map.get(srcName);
            int dest = map.get(destName);

            edges.add(new edge(src, dest, weight));
        }

        Assignment5_2 obj = new Assignment5_2();
        obj.kruskalMST(edges, V, locations);

        sc.close();
    }
}