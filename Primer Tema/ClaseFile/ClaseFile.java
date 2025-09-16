package Tema1.ClaseFile;

import java.io.File;

public class ClaseFile {
    public static void main(String[] args) {
        File carpeta = new File("ClaseFile");
        carpeta.getName(); // Devuelve el nombre del archivo o carpeta
        carpeta.length(); //Longitud en bytes del archivo
        carpeta.getPath(); // Devuelve la ruta desde el archivo que ponemos
        carpeta.getAbsolutePath(); // Devuelve la ruta completa desde C: ...
        carpeta.exists(); //Booleano si existe o no
        carpeta.mkdir(); // Nos crea el directorio con el nombre que le pongamos si no existe
        carpeta.renameTo(new File("File"));
        carpeta.delete();
        // IMPORTANTE //
        carpeta.isFile(); //Devuelve true or false si es un archivo o no
        carpeta.isDirectory(); //Devuelve true or false si es una carpeta o no
        carpeta.listFiles(); //Crea array objetos tipo File de la lista de archivos
        


        //
        
    }
}
