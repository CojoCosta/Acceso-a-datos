import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {
    public static void archivoPorCaracteres(int numeroDeCaracteres) throws IOException {
        try (FileReader fr = new FileReader(new File("prueba.txt"))) {
            int i = 1;
            while ((fr.read()) != -1) {
                try (FileWriter fw = new FileWriter(new File("archivoChar" + (i++) + ".txt"))) {
                    for (int j = 0; j <= numeroDeCaracteres; j++) {
                        fw.write(fr.read());
                    }
                }
            }
        }
    }

    public static void archivoPorLineas(int numeroDeLineas) throws IOException {
        try (Scanner sc = new Scanner(new File("prueba.txt"))) {
            int i = 1;
            while (sc.hasNext()) {
                try (FileWriter fw = new FileWriter(new File("archivoLineas" + (i++) + ".txt"))) {
                    for (int j = 0; j < numeroDeLineas; j++) {
                        fw.write(sc.nextLine());
                        fw.write(System.lineSeparator());
                    }
                }
            }
        }
    }

    public static void unirArchivos(String [] ficheros) throws IOException {
        for (int i = 0; i < ficheros.length; i++) {
            try (Scanner sc = new Scanner(new File(ficheros[i]))) {
                while (sc.hasNext()) {
                    try (FileWriter fw = new FileWriter(new File("archivoUnion.txt"),true)) {
                        fw.write(sc.nextLine());
                        fw.write(System.lineSeparator());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // archivoPorCaracteres(40);
        // archivoPorLineas(3);
        String[] ficheros = {"prueba.txt", "AscendenteSensible.txt"};
        unirArchivos(ficheros);
    }
}