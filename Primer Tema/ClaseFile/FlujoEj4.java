package Tema1.ClaseFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FlujoEj4 {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File("prueba.txt"))) {
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        }
    }
}
