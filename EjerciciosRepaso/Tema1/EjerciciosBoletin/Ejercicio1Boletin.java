import java.io.File;

public class Ejercicio1Boletin {
    public static void showFileDirectories(String ruta){
        File file = new File(ruta);
        File[] directories = file.listFiles();
        for (int i = 0; i < directories.length; i++) {
            if (directories[i].isDirectory()) {
                System.out.printf("Directories: %s\n", directories[i].getName());
            }else if (directories[i].isFile()) {
                System.out.printf("File: %s\n", directories[i].getName());
            }
        }
    }
    public static void main(String[] args) {
        showFileDirectories("EjerciciosRepaso\\Tema2");
    }
}
