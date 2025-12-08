import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class EjercicioRepasoPorLaCara {

    public static void escribirFicheroDat() {
        try (FileOutputStream fos = new FileOutputStream("archivoDat.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new Perro("Hugo", 21, 12.5f));
            oos.writeObject(new Perro("Italiani", 40, 100.5f));
            oos.writeObject(new Perro("Diego", 25, 90.5f));
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void leerArchivoDat(String archivo) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                FileWriter pw = new FileWriter("archivoNuevo.txt")) {
            Perro pr;
            while (true) {
                pr = (Perro) ois.readObject();
                pw.write(pr.toString());
            }
        } catch (IOException e) {
        }
    }

    public static void cambiarItaliani(String archivo, String edadNueva) {
        try (Scanner sc = new Scanner(new File(archivo)); PrintWriter pw = new PrintWriter("archivo2.txt")) {
            String linea = "";
            String[] datosLinea = new String[3];
            String[] edad = new String[2];
            while (sc.hasNext()) {
                linea = sc.nextLine();
                datosLinea = linea.trim().split(";");
                if (datosLinea[0].contains("Italiani")) {
                    edad = datosLinea[1].trim().split(":");
                    edad[1] = edadNueva;
                    pw.write(edad[1]);
                }
                pw.write(linea);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public static void buscarOcurrencias(String archivo, String palabraBuscada){
        try (Scanner sc= new Scanner(new File(archivo))) {
            String palabras = "";
            int cont = 0;
            while (sc.hasNext()) {
                palabras = sc.next().split("\\W+")[0];
                System.out.println(palabras.length());
                if (palabras.equals(palabraBuscada)) {
                    cont ++;
                }
           
            }
            System.out.println("Nº de holas: "+cont);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // escribirFicheroDat();
        // leerArchivoDat("archivoDat.dat");
        // cambiarItaliani("archivoNuevo.txt", "39");
        buscarOcurrencias("aaa.txt","hola");
    }
}
