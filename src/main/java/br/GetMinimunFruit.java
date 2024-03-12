package br;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class GetMinimunFruit {

    public static void main(String[] args) throws IOException, IOException {
        FileInputStream fis = new FileInputStream("C:/Users/PC GAMER/Documents/inputValues3.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/PC GAMER/Documents/fileOutput3.txt"));

        int fruitsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> fruits = IntStream.range(0, fruitsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = getMinimumFruits(fruits);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static int getMinimumFruits(List<Integer> fruits) {
        return 0;

    }
}