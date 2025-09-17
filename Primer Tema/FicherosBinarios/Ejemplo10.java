package FicherosBinarios;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejemplo10 {
    public static void main(String[] args) throws IOException {
        try (DataInputStream din = new DataInputStream(new FileInputStream("ejemplo9.dat"))){
        while (true){
            System.out.printf("Nombre: %s \nNumero de compras: %d \nCredito: %.2f\n", din.readUTF(), din.readInt(), din.readFloat());
        }
            
        } catch (EOFException e) {
            System.out.println("FIN FICHERO");
        }
    }
}
