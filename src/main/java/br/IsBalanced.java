package br;

import java.io.*;
import java.util.Stack;
import java.util.stream.IntStream;

public class IsBalanced {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("C:/Users/PC GAMER/Documents/inputValues.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/PC GAMER/Documents/fileOutput.txt"));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = isBalanced(s);
                System.out.println(isBalanced(s));

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static String isBalanced(String s) {
        if (verifyBrackets(s)) {
            return "YES";
        } else {
            return "NO";
        }
    }

    private static boolean verifyBrackets(String s) {
        Stack<Character> pilha = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char caractere = s.charAt(i);
            if (caractere == '(' || caractere == '[' || caractere == '{') {
                pilha.push(caractere);
            } else if (caractere == ')' || caractere == ']' || caractere == '}') {
                if (pilha.isEmpty() || !correspondente(pilha.pop(), caractere)) {
                    return false;
                }
            }
        }
        return pilha.isEmpty();
    }

    private static boolean correspondente(char abertura, char fechamento) {
        return (abertura == '(' && fechamento == ')') ||
                (abertura == '[' && fechamento == ']') ||
                (abertura == '{' && fechamento == '}');
    }

}