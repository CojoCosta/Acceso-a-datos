package Tema1.ClaseFile;

import java.io.FileReader;
import java.io.IOException;

public class FlujoEj1 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("Tema1/ClaseFile/prueba.txt")) {

            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }

        
        try {
            FileReader fr = new FileReader("Tema1/ClaseFile/prueba.txt");
            int i;
            while ((i = fr.read()) != -1) {
                System.out.print((char) i);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

}
