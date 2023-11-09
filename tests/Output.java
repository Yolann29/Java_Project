package tests;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Output {

    public static void initial(String message) {
        String fileName = "output.txt";
        try (FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(String message) {
        String filePath = "output.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));

            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
