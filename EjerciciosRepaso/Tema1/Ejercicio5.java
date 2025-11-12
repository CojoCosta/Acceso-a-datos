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
                        int asci = (int)palabra.charAt(i+desplazamiento);
                        nuevaPalabra = palabra.replace(palabra.charAt(i),((char)asci));
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
