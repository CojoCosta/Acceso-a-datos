import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio4Boletin {
    public static ArrayList<Character> leerArchivo(String ruta) {
        ArrayList<Character> letras = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(ruta))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                for (int i = 0; i < linea.length(); i++) {
                    letras.add(linea.charAt(i));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no existente");
        }
        return letras;
    }

    public static void contarCaracteres(ArrayList<Character> letras) {
        char letraMasRepe = ' ';
        int numeroDeRepes = 0;
        int contador = 0;
        for (Character letra : letras) {
            for (int i = 0; i < letras.size(); i++) {
                if (letra == letras.get(i)) {
                    contador ++;
                }
            }
            if (contador > numeroDeRepes) {
                numeroDeRepes = contador;
                letraMasRepe = letra;
            }
        }
        System.out.printf("La letra mas repetida es \"%s\" que se repite %d veces", letraMasRepe, numeroDeRepes);
    }

    public static void main(String[] args) {
        contarCaracteres(leerArchivo("EjerciciosRepaso\\Tema1\\hola.txt"));

    }
}
