package EjerciciosRepaso.Tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ejercicio5 {

    public static void codigoCesar(String file, int desplazamiento) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(file))) {
            String[] palabras;
            String nuevaPalabra = "";
            while (sc.hasNextLine()) {
                palabras = sc.nextLine().split(" ");
                for (String palabra: palabras) {
                    for (int i = 0; i < palabra.length(); i++) {
                        
                        nuevaPalabra = palabra.replace(palabra.charAt(i), palabra.charAt(i+desplazamiento));
                    }
                    System.out.print(nuevaPalabra);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        codigoCesar("EjerciciosRepaso\\Tema1\\hola.txt", 1);
    }
}
