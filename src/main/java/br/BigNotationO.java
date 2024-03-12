package br;

public class BigNotationO {
    // Exemplo de função que tem complexidade O(n)
    public static int somaArray(int[] array) {
        int soma = 0;
        for (int i = 0; i < array.length; i++) {
            soma += array[i];
        }
        return soma;
    }

    // Exemplo de função que tem complexidade O(n^2)
    public static void imprimirPares(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                System.out.println("(" + array[i] + ", " + array[j] + ")");
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};

        // Complexidade O(n)
        int soma = somaArray(array);
        System.out.println("Soma do array: " + soma);

        // Complexidade O(n^2)
        imprimirPares(array);

        int n = 10;
        System.out.println("Fibonacci de " + n + " é " + fibonacci(n));

        int[][] matrix = {
                {1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        int targetColor = 1; // Cor de destino a ser preenchida
        int replacementColor = 2; // Nova cor a ser aplicada

        System.out.println("Matriz original:");
        printMatrix(matrix);

        // Aplicando o algoritmo de Flood Fill a partir da posição (2,2)
        floodFill(matrix, 2, 2, targetColor, replacementColor);

        System.out.println("\nMatriz após Flood Fill:");
        printMatrix(matrix);

    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            System.out.println("fibonacci " + dp[i]);
        }
        return dp[n];
    }

    public static void floodFill(int[][] matrix, int row, int col, int targetColor, int replacementColor) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Verifica se as coordenadas estão dentro da matriz e se a cor é a cor de destino
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[row][col] != targetColor) {
            return;
        }

        // Preenche a célula atual com a nova cor
        matrix[row][col] = replacementColor;

        // Aplica recursivamente o Flood Fill para as células vizinhas
        floodFill(matrix, row + 1, col, targetColor, replacementColor); // baixo
        floodFill(matrix, row - 1, col, targetColor, replacementColor); // cima
        floodFill(matrix, row, col + 1, targetColor, replacementColor); // direita
        floodFill(matrix, row, col - 1, targetColor, replacementColor); // esquerda
    }

    // Função auxiliar para imprimir a matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}