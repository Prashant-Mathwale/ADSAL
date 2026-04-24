import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Scanner;

import static java.lang.Math.max;

public class BST
{

    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        if (val < root.data) {
            root.left = insert(root.left, val);
        }
        else if (val > root.data) {
            root.right = insert(root.right, val);
        }
        else {
            System.out.println("Duplication is not allowed: " + val);
        }
        return root;
    }

    void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    void preorder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    void Postorder(Node root) {
        if (root == null) return;

        Postorder(root.left);
        Postorder(root.right);
        System.out.print(root.data + " ");

    }

    void search(Node root, int key) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        Node temp = root;
        while (temp != null) {
            if (temp.data == key) {
                System.out.println(key + " is present in the tree");
                return;
            }
            else if (key < temp.data) {
                temp = temp.left;
            }
            else {
                temp = temp.right;
            }
        }
        System.out.println(key + " is NOT present in the tree");
    }

    Node createCopy(Node root){
        if(root == null)return null;
        Node newRoot = new Node(root.data) ;

        newRoot.left = createCopy(root.left);
        newRoot.right = createCopy(root.right);
        return newRoot;
    }

    void mirrorImage(Node root){
        if(root == null)return ;

        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirrorImage(root.left);
        mirrorImage(root.right);

    }

    void DisplayParentWithChildren(Node root){
        if(root == null)return ;

        if(root.left != null || root.right != null){
            System.out.println("Parent "+root.data);

            if(root.left != null){
                System.out.println("Left Child ="+root.left.data);
            }
            else{
                System.out.println("Left Child = NULL");
            }
            if(root.right != null){
                System.out.println("Right Child =" +root.right.data);
            }
            else{
                System.out.println("Right Child = NULL");
            }
        }
        DisplayParentWithChildren(root.left);
        DisplayParentWithChildren(root.right);
    }

    void LevelOrder(Node root){
        Queue<Node> q= new ArrayDeque<>() ;
        if(root == null){
            System.out.println("Empty BST");
            return ;
        }
        q.offer(root);
        while(!q.isEmpty()){
            Node temp = q.poll();
            System.out.print(temp.data +" ");

            if(temp.left != null){
                q.offer(temp.left);
            }
            if(temp.right != null){
                q.offer(temp.right);
            }
        }
    }

    int Depth(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = Depth(root.left);
        int rightHeight = Depth(root.right);
        return 1+max(leftHeight,rightHeight);
    }

    void DeleteBST(Node root , int key){
        if(root == null){
            System.out.println("Tree is empty");
            return ;
        }
        Node ptr = root;
        Node parent = null;

        while(ptr != null && ptr.data != key){
            parent = ptr;
            if(key > ptr.data){
                ptr = ptr.right;
            }
            else{
                ptr = ptr.left;
            }
        }

        if(ptr == null){
            System.out.println("Key doesnt exit");
            return ;
        }

        if(ptr.left == null && ptr.right == null){
            if(parent == null){
                root = null;
            }
            else if(parent.left == ptr){
                parent.left =null;
            }
            else if(parent.right == ptr){
                parent.right=null;
            }
        }

        if(ptr.left == null || ptr.right == null){
            Node child = null;
            if(ptr.right != null){
                child = ptr.right;
            }
            else{
                child = ptr.left;
            }

            if(parent == null){
                root = child;
                return ;
            }
            else if(parent.left == ptr){
                parent.left = child;
            }
            else if(parent.right == ptr){
                parent.right = child;
            }
            ptr.left = null;
            ptr.right = null;
        }
        else {
            Node successorParent = ptr;
            Node successor = ptr.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            ptr.data = successor.data;

            if (successorParent.left == successor)
                successorParent.left = successor.right;
            else
                successorParent.right = successor.right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST b = new BST();
        Node root = null;

        int ch;
        do{
            System.out.print("\n\n1. Create\n2. Insert\n3. Inorder traversal\n4. Preorder Traversal " +
                    "\n5. Postorder Traversal\n6. Search\n7. Mirror Image\n8. copy BST" +
                    "\n9. DisplayParentWithChildren\n10. LevelOrder Traversal \n11. Depth of BST" +
                    "\n12. Exit\nEnter your Choice: ");
            Scanner s=new Scanner(System.in);
            ch=s.nextInt();

            switch (ch){
                case 1:{
                    System.out.print("Enter number of nodes: ");
                    int n = sc.nextInt();

                    System.out.println("Enter the values:");
                    for (int i = 0; i < n; i++) {
                        int val = sc.nextInt();
                        root = b.insert(root, val);
                    }
                    break;
                }
                case 2:{
                    System.out.println("Enter the value to insert:");
                    int val = sc.nextInt();
                    b.insert(root, val);
                    break;
                }
                case 3:{
                    System.out.print("\nInorder Traversal: ");
                    b.inorder(root);
                    break;
                }
                case 4:{
                    System.out.print("\npreorder Traversal: ");
                    b.preorder(root);
                    break;
                }

                case 5:{
                    System.out.print("\nPostorder Traversal: ");
                    b.Postorder(root);
                    break;
                }

                case 6:{
                    System.out.print("\nEnter value to search: ");
                    int key = sc.nextInt();
                    b.search(root, key);
                    break;
                }
                case 7:{
                    b.mirrorImage(root);
                    System.out.println("\nInorder traversal after mirrorImage");
                    b.inorder(root);
                }

                case 8:{
                    Node newRoot = b.createCopy(root);
                    System.out.println("\nInorder Traversal of copied BST");
                    b.inorder(newRoot);
                }
                case 9:{
                    b.DisplayParentWithChildren(root);
                }
                case 10:{
                    b.LevelOrder(root);
                }
                case 11:{
                    int h = b.Depth(root);
                    System.out.println("Depth of BST :"+h);

                }
                case 12:{
                    System.out.println("Exiting");
                    break;
                }
                default:{
                    System.out.println("Invalid Choice");
                    break;
                }
            }
        }while(ch!=12);

        sc.close();
    }
}