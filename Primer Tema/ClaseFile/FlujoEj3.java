package Tema1.ClaseFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FlujoEj3 {
    public static void main(String[] args) {
        /*FileWriter va de caracter en caracter */
        String cad = "Girona 0 - 1 Celta";
        try (FileWriter fw = new FileWriter("archivo.txt", true)){
            for (int i = 0; i < cad.length(); i++) {
                fw.write(cad.charAt(i));
            }
            // fw.write(cad); //asi tambien funciona
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error");
        }
        /*EJERCICIO */
        try (FileReader fr = new FileReader("prueba.txt")){
            
            int i;
            int cont = 0;
            char letra = 'i';
            while ((i = fr.read()) != -1) {
                if (letra == i) {
                    cont ++;
                }
            }
            System.out.println(cont);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
