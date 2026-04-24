import java.util.*;

class HeapScheduler {

    static ArrayList<Integer> minHeap = new ArrayList<>();
    static ArrayList<Integer> maxHeap = new ArrayList<>();


    static void insertMin(int element) {
        minHeap.add(element);
        int index = minHeap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (minHeap.get(index) < minHeap.get(parent)) {
                Collections.swap(minHeap, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // -------- DELETE MIN ----------
    static void deleteMin() {
        if (minHeap.size() == 0) {
            System.out.println("Min Heap is empty");
            return;
        }

        System.out.println("Deleted element: " + minHeap.get(0));

        int last = minHeap.remove(minHeap.size() - 1);

        if (minHeap.size() > 0) {
            minHeap.set(0, last);
            heapifyMin(0);
        }
    }

    static void heapifyMin(int i) {
        int smallest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < minHeap.size() && minHeap.get(left) < minHeap.get(smallest))
            smallest = left;

        if (right < minHeap.size() && minHeap.get(right) < minHeap.get(smallest))
            smallest = right;

        if (smallest != i) {
            Collections.swap(minHeap, i, smallest);
            heapifyMin(smallest);
        }
    }

    // -------- MAX HEAP INSERT ----------
    static void insertMax(int element) {
        maxHeap.add(element);
        int index = maxHeap.size() - 1;

        while (index > 0) {
            int parent = (index - 1) / 2;

            if (maxHeap.get(index) > maxHeap.get(parent)) {
                Collections.swap(maxHeap, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // -------- DELETE MAX ----------
    static void deleteMax() {
        if (maxHeap.size() == 0) {
            System.out.println("Max Heap is empty");
            return;
        }

        System.out.println("Deleted element: " + maxHeap.get(0));

        int last = maxHeap.remove(maxHeap.size() - 1);

        if (maxHeap.size() > 0) {
            maxHeap.set(0, last);
            heapifyMax(0);
        }
    }

    static void heapifyMax(int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < maxHeap.size() && maxHeap.get(left) > maxHeap.get(largest))
            largest = left;

        if (right < maxHeap.size() && maxHeap.get(right) > maxHeap.get(largest))
            largest = right;

        if (largest != i) {
            Collections.swap(maxHeap, i, largest);
            heapifyMax(largest);
        }
    }

    // -------- DISPLAY ----------
    static void displayMin() {
        System.out.println("Min Heap: " + minHeap);
    }

    static void displayMax() {
        System.out.println("Max Heap: " + maxHeap);
    }

    // -------- MAIN SCHEDULER ----------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- HEAP SCHEDULER -----");
            System.out.println("1. Insert in Min Heap");
            System.out.println("2. Delete Min");
            System.out.println("3. Insert in Max Heap");
            System.out.println("4. Delete Max");
            System.out.println("5. Display Min Heap");
            System.out.println("6. Display Max Heap");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter element: ");
                    insertMin(sc.nextInt());
                    break;

                case 2:
                    deleteMin();
                    break;

                case 3:
                    System.out.print("Enter element: ");
                    insertMax(sc.nextInt());
                    break;

                case 4:
                    deleteMax();
                    break;

                case 5:
                    displayMin();
                    break;

                case 6:
                    displayMax();
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}