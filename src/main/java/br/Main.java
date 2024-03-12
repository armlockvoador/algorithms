package br;

import br.binary.tree.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        levelOrder(root);
        System.out.println(height);
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.getData()) {
                cur = insert(root.getLeft(), data);
                root.setLeft(cur);
            } else {
                cur = insert(root.getRight(), data);
                root.setRight(cur);
            }
            return root;
        }
    }

    public static int height(Node node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }

    public static void levelOrder(Node root) {
        if (root != null) {
            Deque<Node> queue = new ArrayDeque<>();
            queue.push(root);
            while (!queue.isEmpty()) {
                Node actual = queue.removeFirst();
                System.out.printf("%s ", actual.getData());
                if (actual.getLeft() != null) {
                    queue.add(actual.getLeft());
                }
                if (actual.getRight() != null) {
                    queue.add(actual.getRight());
                }
            }
        }
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getData());
            inOrder(root.getRight());
        }
    }

    public void preOrder(Node root) {
        if (root != null) {
            System.out.println(root.getData());
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public void posOrder(Node root) {
        if (root != null) {
            posOrder(root.getLeft());
            posOrder(root.getRight());
            System.out.println(root.getData());
        }
    }

}