package Tema1;



import java.io.File;

public class Ejercicio1 {
    public static void main(String[] args) {
        File archivo = new File("Tema1");
        File[] carpetas = archivo.listFiles();
        
        for (int i = 0; i < carpetas.length; i++) {
            if (carpetas[i].isFile()) {
                System.out.println("Nombre archivo: " + carpetas[i].getName());
            }
        }
        for (int i = 0; i < carpetas.length; i++) {
            if (carpetas[i].isDirectory()) {
                System.out.println("Nombre directorio: " + carpetas[i].getName());
            }
        }
    }
}
