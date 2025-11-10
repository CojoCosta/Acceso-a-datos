package EjerciciosRepaso.Tema1;

import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio2 {
    public static void crearArchivosTxt(String carpeta, int numeroArchivos) throws IOException{

        for (int i = 1; i <= numeroArchivos; i++) {
            try(FileWriter fw = new FileWriter(carpeta+"nombre"+i+".txt")){
                fw.write("Este es el fichero nombre"+i+".txt");
            }
        }
    }
    public static void main(String[] args) throws IOException{
        crearArchivosTxt("EjerciciosRepaso\\Tema1\\", 3);
    }
}
