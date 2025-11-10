import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio11 {
    public static void pruebaBufferStream() throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prueba100MB.txt"))) {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prueba100MB2.txt"))) {
                while ((bis.read()) != -1) {
                    bos.write(bis.read());
                }
            }
        }
    }

    public static void pruebaFileStream(char[] bf) throws IOException {
        int i;
        try (FileInputStream fis = new FileInputStream("prueba100MB.txt")) {
            try (FileOutputStream fos = new FileOutputStream("prueba100MB3.txt")) {
                while ((i = fis.read()) != -1) {
                    System.out.print(new String(bf, 0, i));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        char buffer10[] = new char[10];
        char buffer100[] = new char[100];
        char buffer1000[] = new char[1000];

        long tiempoI = System.nanoTime();
        pruebaBufferStream();
        long tiempoF = System.nanoTime();
        System.out.println("Tiempo BufferedInputStream/ BufferedOutputStream: " + (tiempoF - tiempoI) / 10000000 + "n/s");

        long tiempoI10 = System.nanoTime();
        pruebaFileStream(buffer10);
        long tiempoF10 = System.nanoTime();
        System.out.println("Tiempo FileInputStream/FileOutputStream(10): " + (tiempoF10 - tiempoI10) / 10000000 + "n/s");

        long tiempoI100 = System.nanoTime();
        pruebaFileStream(buffer100);
        long tiempoF100 = System.nanoTime();
        System.out.println("Tiempo FileInputStream/FileOutputStream(100): " + (tiempoF100 - tiempoI100) / 10000000 + "n/s");

        long tiempoI1000 = System.nanoTime();
        pruebaFileStream(buffer1000);
        long tiempoF1000 = System.nanoTime();
        System.out.println("Tiempo FileInputStream/FileOutputStream(1000): " + (tiempoF1000 - tiempoI1000) / 10000000 + "n/s");
    }
}