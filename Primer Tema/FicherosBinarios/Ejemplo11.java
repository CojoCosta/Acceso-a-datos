package FicherosBinarios;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ejemplo11 {
    public static void main(String[] args) {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("ejemplo9.dat")); DataInputStream din = new DataInputStream(new FileInputStream("ejemplo9.dat"))){
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Diego", 7, 2000.20f));
        clientes.add(new Cliente("Diego1", 71, 2001.20f));
        clientes.add(new Cliente("Diego2", 72, 2002.20f));
        clientes.add(new Cliente("Diego3", 73, 2003.20f));
        for (Cliente cliente : clientes) {
            dout.writeUTF(cliente.getNombre());
            dout.writeInt(cliente.getNumCompras());
            dout.writeFloat(cliente.getCredito());
        }

        while (true){
            System.out.printf("Nombre: %s \nNumero de compras: %d \nCredito: %.2f\n", din.readUTF(), din.readInt(), din.readFloat());
        }
        } catch (IOException e) {
            System.out.println("FIN FICHERO");
        }
    }
}
