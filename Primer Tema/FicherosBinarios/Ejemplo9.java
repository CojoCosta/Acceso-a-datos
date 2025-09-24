package FicherosBinarios;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Alumno;

public class Ejemplo9 {
    public static void main(String[] args) {
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("ejemplo9.dat"))){
        ArrayList<Alumno> clientes = new ArrayList<>();
        clientes.add(new Alumno("Diego", 7, 2000.20f));
        for (Alumno cliente : clientes) {
            dout.writeUTF(cliente.getNombre());
            dout.writeInt(cliente.getCodigo());
            dout.writeFloat(cliente.getAltura());
        }
        } catch (IOException e) {
            
        }
    }
}
