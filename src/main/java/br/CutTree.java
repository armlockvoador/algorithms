package br;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CutTree {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/PC GAMER/Documents/fileOutput4.txt"));
        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[][] edges = new int[n-1][2];

        for (int edgesRowItr = 0; edgesRowItr < n-1; edgesRowItr++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");

            for (int edgesColumnItr = 0; edgesColumnItr < 2; edgesColumnItr++) {
                int edgesItem = Integer.parseInt(edgesRowItems[edgesColumnItr].trim());
                edges[edgesRowItr][edgesColumnItr] = edgesItem;
            }
        }

        int result = cuttree(n, k, edges);
        System.out.println("quantidade de subárvores: "+result);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }

    static int result = 0;

    static void dfs(int node, int parent, int k, List<List<Integer>> adjList, int[][] dp) {
        dp[node][0] = 1;

        for (int child : adjList.get(node)) {
            if (child != parent) {
                dfs(child, node, k, adjList, dp);
                for (int i = k; i >= 1; i--) {
                    int sum = 0;
                    for (int j = 0; j < i; j++) {
                        sum += dp[child][j] * dp[node][i - j - 1];
                    }
                    dp[node][i] += sum;
                }
            }
        }
        result += dp[node][k];
        result += dp[node][0]; // Adiciona 1 para a própria subárvore vazia
    }

    static int cuttree(int n, int k, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int[][] dp = new int[n + 1][k + 1];
        dfs(1, -1, k, adjList, dp);
        return result;
    }
}