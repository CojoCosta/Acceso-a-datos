
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5Boletin {
    public static void main(String[] args) {
        String cadena = "donde";
        int numeroDeLinea = 0;
        String path = "EjerciciosRepaso\\Tema1\\hola.txt";
        try (Scanner sc = new Scanner(new File(path))) {
            while (sc.hasNext()) {
                String lineaActual = sc.nextLine();
                numeroDeLinea++;
                if (lineaActual.contains(cadena)) {
                    System.out.printf("La cadena: \"%s\" está en la linea %d", cadena, numeroDeLinea);
                }
            }
        } catch (IOException e) {
            System.out.println("AAAAAAAAAAAAA");
        }
    }
}
