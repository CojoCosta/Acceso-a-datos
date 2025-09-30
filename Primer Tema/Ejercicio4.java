import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio4 {

    public static ArrayList<Character> leerArchivo (String archivo){
        ArrayList<Character> chars = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(archivo))){
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                for (int i = 0; i < linea.length(); i++) {
                    chars.add(linea.charAt(i));
                }
            }
        } catch (Exception e) {
            System.out.println("No se encuentra ningun archivo");
        }
        return chars;
    }
    
    public static void charMasRepetido(ArrayList<Character> chars){
        char mayor = ' ';
        int masRepes = 0;
        for (Character character : chars) {
            int contador = 0;
            for (int i = 0; i < chars.size(); i++) {
                if (character == chars.get(i)) {
                    contador++;
                }
            }
            if (contador > masRepes) {
                masRepes = contador;
                mayor = character;
            }
        }
        System.out.println("Carácter más repetido: '" + mayor + "' con " + masRepes + " repeticiones.");
    }
    
    public static void main(String[] args) {
        charMasRepetido(leerArchivo("ejemplo.txt"));
    }
}