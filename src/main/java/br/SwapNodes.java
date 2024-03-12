package br;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SwapNodes {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/PC GAMER/Documents/inputValues1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/PC GAMER/Documents/fileOutput1.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = swapNodes(indexes, queries);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        List<TreeNode> treeNodes = buildTree(indexes);
        List<List<Integer>> result = new ArrayList<>();

        for (int query : queries) {
            swapSubtrees(treeNodes.get(0), 1, query);
            List<Integer> traversal = inorderTraversal(treeNodes.get(0));
            result.add(traversal);
        }

        return result;
    }

    private static List<TreeNode> buildTree(List<List<Integer>> indexes) {
        List<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(new TreeNode(1)); // Root node
        for (int i = 0; i < indexes.size(); i++) {
            TreeNode currentNode = treeNodes.get(i);
            List<Integer> currentNodeIndexes = indexes.get(i);
            int leftIndex = currentNodeIndexes.get(0);
            int rightIndex = currentNodeIndexes.get(1);

            if (leftIndex != -1) {
                TreeNode leftChild = new TreeNode(leftIndex);
                currentNode.left = leftChild;
                treeNodes.add(leftChild);
            }

            if (rightIndex != -1) {
                TreeNode rightChild = new TreeNode(rightIndex);
                currentNode.right = rightChild;
                treeNodes.add(rightChild);
            }
        }
        return treeNodes;
    }

    private static void swapSubtrees(TreeNode root, int depth, int query) {
        if (root == null) return;

        if (depth % query == 0) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        swapSubtrees(root.left, depth + 1, query);
        swapSubtrees(root.right, depth + 1, query);
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);
        return result;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;

        inorderTraversal(root.left, result);
        result.add(root.value);
        inorderTraversal(root.right, result);
    }
}
