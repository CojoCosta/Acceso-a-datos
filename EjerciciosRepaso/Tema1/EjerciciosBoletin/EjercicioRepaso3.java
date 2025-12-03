import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EjercicioRepaso3 {
    public static String buscarPalabras(String archivo, String palabraBuscada) throws IOException {
        File fichero = new File(archivo);
        int cont = 0;
        Scanner sc = new Scanner(fichero);
        String[] buffer = new String[100];
        int i = 0;
        while (sc.hasNext()) {
            buffer[i] = sc.next().split("\\W+")[0];
            if (buffer[i].equals(palabraBuscada)) {
                cont++;
            }
            i++;
        }
        return String.format("La palabra está escrita %d veces", cont);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(buscarPalabras("Tema1\\hola.txt", "hola"));
    }
}
