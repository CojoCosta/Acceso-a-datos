package EjerciciosRepaso.Tema1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EjercicioExamen {
    public static String readTab(String fichero, int pos) throws IOException {
        String[] cadaElemento;
        int val = 0;
        double precio = 0;
        String nombre = "";
        int contarLineas = 1;
        try (Scanner sc = new Scanner(new File(fichero))) {
            String line1 = sc.nextLine();
            while (sc.hasNextLine()) {
                cadaElemento = sc.nextLine().split("\t");
                if (contarLineas == pos) {
                    val = Integer.parseInt(cadaElemento[0]);
                    precio = Double.parseDouble(cadaElemento[1]);
                    nombre = cadaElemento[2];
                }
                contarLineas++;
            }
        }
        return String.format("Nombre: %s, multiplicacion: %f", nombre, val * precio);
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readTab("EjerciciosRepaso\\Tema1\\juegos.txt", 3));

    }
}
