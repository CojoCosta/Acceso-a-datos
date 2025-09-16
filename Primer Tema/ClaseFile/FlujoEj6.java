package Tema1.ClaseFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FlujoEj6 {
    public static void main(String[] args) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("prueba.txt",true))){
            pw.println("que pasa chavales");
        } catch (IOException e) {
            System.out.println("Error");
        }

        /*EJERCICIO 5*/
        try (Scanner sc = new Scanner("prueba.txt")){
            String cad = "Italia";
            while (sc.hasNext()) {
                if (cad == ) {
                    
                }
            }
        }
    }
}