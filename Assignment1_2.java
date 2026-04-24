import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;


public class IBST {

    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node insert(Node root, int val) {
        Node newNode = new Node(val);

        if (root == null)
            return newNode;

        Node parent = null;
        Node curr = root;

        while (curr != null) {
            parent = curr;
            if (val < curr.data)
                curr = curr.left;
            else if (val > curr.data)
                curr = curr.right;
            else {
                System.out.println("Duplication is not allowed: " + val);
                return root;
            }
        }

        if (val < parent.data)
            parent.left = newNode;
        else
            parent.right = newNode;

        return root;
    }

    void inorder(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }

    void preorder(Node root) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.data + " ");

            if (curr.right != null)
                stack.push(curr.right);
            if (curr.left != null)
                stack.push(curr.left);
        }
    }

    void Postorder(Node root) {
        if (root == null) return;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            s2.push(curr);

            if (curr.left != null)
                s1.push(curr.left);
            if (curr.right != null)
                s1.push(curr.right);
        }

        while (!s2.isEmpty())
            System.out.print(s2.pop().data + " ");
    }

    void search(Node root, int key) {
        Node temp = root;


        while (temp != null) {
            if (temp.data == key) {
                System.out.println(key + " is present in the tree");
                return;
            } else if (key < temp.data)
                temp = temp.left;
            else
                temp = temp.right;
        }
        System.out.println(key + " is NOT present in the tree");
    }

    Node createCopy(Node root) {
        if (root == null) return null;

        Node newRoot = new Node(root.data);
        Queue<Node> q1 = new ArrayDeque<>();
        Queue<Node> q2 = new ArrayDeque<>();

        q1.offer(root);
        q2.offer(newRoot);

        while (!q1.isEmpty()) {
            Node orig = q1.poll();
            Node copy = q2.poll();

            if (orig.left != null) {
                copy.left = new Node(orig.left.data);
                q1.offer(orig.left);
                q2.offer(copy.left);
            }

            if (orig.right != null) {
                copy.right = new Node(orig.right.data);
                q1.offer(orig.right);
                q2.offer(copy.right);
            }
        }
        return newRoot;
    }

    void mirrorImage(Node root) {
        if (root == null) return;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            Node temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;

            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
    }

    void DisplayParentWithChildren(Node root) {
        if (root == null) return;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.left != null || curr.right != null) {
                System.out.println("Parent " + curr.data);
                System.out.println("Left Child = " + (curr.left != null ? curr.left.data : "NULL"));
                System.out.println("Right Child = " + (curr.right != null ? curr.right.data : "NULL"));
            }

            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
    }

    void LevelOrder(Node root) {
        if (root == null) {
            System.out.println("Empty BST");
            return;
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.data + " ");

            if (temp.left != null) q.offer(temp.left);
            if (temp.right != null) q.offer(temp.right);
        }
    }

    int Depth(Node root) {
        if (root == null) return 0;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            depth++;

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                if (curr.left != null) q.offer(curr.left);
                if (curr.right != null) q.offer(curr.right);
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IBST b = new IBST();
        Node root = null;
        int ch;

        do {
            System.out.print("\n\n1. Create\n2. Insert\n3. Inorder traversal\n4. Preorder Traversal" +
                    "\n5. Postorder Traversal\n6. Search\n7. Mirror Image\n8. Copy BST" +
                    "\n9. Display Parent With Children\n10. Level Order Traversal" +
                    "\n11. Depth of BST\n12. Exit\nEnter your choice: ");

            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter number of nodes: ");
                    int n = sc.nextInt();
                    System.out.println("Enter values:");
                    for (int i = 0; i < n; i++) {
                        root = b.insert(root, sc.nextInt());
                    }
                    break;

                case 2:
                    System.out.print("Enter value to insert: ");
                    root = b.insert(root, sc.nextInt());
                    break;

                case 3:
                    System.out.print("Inorder: ");
                    b.inorder(root);
                    break;

                case 4:
                    System.out.print("Preorder: ");
                    b.preorder(root);
                    break;

                case 5:
                    System.out.print("Postorder: ");
                    b.Postorder(root);
                    break;

                case 6:
                    System.out.print("Enter value to search: ");
                    b.search(root, sc.nextInt());
                    break;

                case 7:
                    b.mirrorImage(root);
                    System.out.print("Inorder after mirror: ");
                    b.inorder(root);
                    break;

                case 8:
                    Node copyRoot = b.createCopy(root);
                    System.out.print("Inorder of copied BST: ");
                    b.inorder(copyRoot);
                    break;

                case 9:
                    b.DisplayParentWithChildren(root);
                    break;

                case 10:
                    b.LevelOrder(root);
                    break;

                case 11:
                    System.out.println("Depth of BST: " + b.Depth(root));
                    break;

                case 12:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (ch != 12);

        sc.close();
    }
}
