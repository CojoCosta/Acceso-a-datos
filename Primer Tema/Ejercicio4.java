import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Ejercicio4 {
    public static void main(String[] args) {
        FileReader fr = null;
        Map<Character, Integer> frecuencias = new HashMap<>();
        try {
            fr = new FileReader("ejemplo.txt");
            int codigoCaracter;
            while ((codigoCaracter = fr.read()) != -1) {
                char caracterLeido = (char) codigoCaracter;
                frecuencias.put(caracterLeido, frecuencias.getOrDefault(caracterLeido, 0) + 1); // Pone en la lista de frecuencias el caracter leído, si ya estaba antes le sube 1 a su frecuencia sin añadir un nuevo  elemento a la lista
            }

            fr.close();
        } catch (IOException e) {
            System.out.println("error al leer el archivo");
        }
        char caracterMasFrecuente = ' '; // Inicializa un valor por defecto
        int maxFrecuencia = 0;
        for (Map.Entry<Character, Integer> entry : frecuencias.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                caracterMasFrecuente = entry.getKey();
            }
        }
        System.out.println(
                "El caracter mas usado es: " + caracterMasFrecuente + " con una frecuencia de: " + maxFrecuencia);
    }
}