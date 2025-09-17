package FicherosBinarios;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Ejemplo9 {
    public static void main(String[] args) {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("ejemplo9.dat"))){
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Diego", 7, 2000.20f));
        for (Cliente cliente : clientes) {
            dout.writeUTF(cliente.getNombre());
            dout.writeInt(cliente.getNumCompras());
            dout.writeFloat(cliente.getCredito());
        }
        } catch (IOException e) {
            
        }
    }
}
