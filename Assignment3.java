class ThreadedBinaryTree {

    // Node structure
    static class Node {
        int data;
        Node left, right;
        boolean leftThread, rightThread;

        Node(int data) {
            this.data = data;
            left = right = null;
            leftThread = false;
            rightThread = false;
        }
    }

    private Node root;
    private Node prev;

    public void setRoot(Node node) {
        root = node;
    }

    public void createThreads() {
        prev = null;
        createThreadsUtil(root);
    }

    private void createThreadsUtil(Node current) {
        if (current == null)
            return;

        createThreadsUtil(current.left);

        // Left thread if left child is null
        if (current.left == null) {
            current.left = prev;
            current.leftThread = true;
        }

        // Right thread of previous node if right child is null
        if (prev != null && prev.right == null) {
            prev.right = current;
            prev.rightThread = true;
        }

        prev = current;

        // Right subtree
        createThreadsUtil(current.right);
    }

    // Leftmost node helper
    private Node leftMost(Node node) {
        while (node != null && !node.leftThread && node.left != null)
            node = node.left;
        return node;
    }

    // Inorder traversal
    public void inorder() {
        Node current = leftMost(root);

        while (current != null) {
            System.out.print(current.data + " ");

            if (current.rightThread)
                current = current.right;
            else
                current = leftMost(current.right);
        }
        System.out.println();
    }

    // Preorder traversal
    public void preorder() {
        Node current = root;

        while (current != null) {
            System.out.print(current.data + " ");

            if (current.left != null && !current.leftThread)
                current = current.left;
            else if (current.right != null && !current.rightThread)
                current = current.right;
            else {
                while (current != null && current.rightThread)
                    current = current.right;
                if (current != null)
                    current = current.right;
            }
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();

        // Manually create nodes
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);
        Node n5 = new Node(50);

        // Manually build the binary tree
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;

        // Set the root
        tree.setRoot(n1);

        // Create threads
        tree.createThreads();

        System.out.println("Inorder Traversal:");
        tree.inorder();

        System.out.println("Preorder Traversal:");
        tree.preorder();
    }
}
