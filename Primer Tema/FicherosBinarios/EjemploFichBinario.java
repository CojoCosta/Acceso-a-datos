package FicherosBinarios;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploFichBinario {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("prueba.txt");
        FileOutputStream fout = new FileOutputStream("fichero.dat", true)){
            // for (int i = fin.read(); i > -1 ; i--) {
            //     fout.write(i);
            // }
            int i;
            while ((i = fin.read()) != -1) {
                fout.write(i);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
