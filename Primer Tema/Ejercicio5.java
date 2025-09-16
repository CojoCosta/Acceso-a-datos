
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        String archivo = "prueba.txt";
        String cadena = "Carlos";
        int numeroLineas = 0;
        try (Scanner sc2 = new Scanner(new File(archivo))) {
            while (sc2.hasNext()) {
                String lineaActual = sc2.nextLine();
                numeroLineas++;
                if (lineaActual.contains(cadena)) {
                    System.out.printf("La cadena \"%s\" aparece en la  %d \n", cadena, numeroLineas);

                }
                //#region CON SPLIT
                // String[] palabras = lineaActual.split("\\W+");
                // for (String palabra : palabras) {
                //     if (palabra.equalsIgnoreCase(cadena)) {
                //         System.out.printf("La cadena \"%s\" aparece en la  %d", cadena, numeroLineas);
                //     }
                // }
                //#endregion
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
