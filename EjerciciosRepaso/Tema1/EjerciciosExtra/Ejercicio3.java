package EjerciciosRepaso.Tema1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void buscaPalabras(String fichero, String palabraBuscada) throws FileNotFoundException {
        int cont = 0;
        try (Scanner sc = new Scanner(new File(fichero))) {
            String[] buffer;
            while (sc.hasNextLine()) {
                buffer = sc.nextLine().split(" ");
                for (String palabra : buffer) {
                    if (palabra.equals(palabraBuscada)) {
                        cont++;
                    }
                }
            }
        }
        System.out.printf("%s : %d veces",palabraBuscada, cont);
    }

    public static void main(String[] args) throws IOException {
        buscaPalabras("EjerciciosRepaso\\Tema1\\hola.txt","hola");
    }
}
