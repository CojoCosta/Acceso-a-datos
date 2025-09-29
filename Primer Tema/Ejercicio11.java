import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio11 {
    public static void pruebaBufferStream() throws IOException{
        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prueba100MB.txt"))){
            try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prueba100MB2.txt"))){
                while ((bis.read()) != -1) {
                    bos.write(bis.read());
                }
            }
        }
    }
    public static void pruebaFileStream() throws IOException{
        try(FileInputStream fis = new FileInputStream("prueba100MB.txt")){
            try(FileOutputStream fos = new FileOutputStream("prueba100MB3.txt")){
                while (fis.read() != -1) {
                    fos.write(fis.read());
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        long tiempoI = System.nanoTime();
        pruebaBufferStream();
        long tiempoF = System.nanoTime();
        System.out.println("Tiempo BufferedInputStream/ BufferedOutputStream: " + (tiempoF - tiempoI)/1000000 + "n/s");
        long tiempoInicio = System.nanoTime();
        pruebaFileStream();
        long tiempoFinal = System.nanoTime();
        System.out.println("Tiempo FileInputStream/FileOutputStream: " + (tiempoFinal - tiempoInicio)/1000000 + "n/s");

    }
}
