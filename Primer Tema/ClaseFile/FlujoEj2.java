package Tema1.ClaseFile;

import java.io.FileReader;
import java.io.IOException;

public class FlujoEj2 {
        public static void main(String[] args) {
        try (FileReader fr = new FileReader("Tema1/ClaseFile/prueba.txt")) {

            char buffer[] = new char[5];
            int i;
            while ((i = fr.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, i));
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
