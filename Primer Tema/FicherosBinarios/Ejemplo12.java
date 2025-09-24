package FicherosBinarios;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Cliente;

public class Ejemplo12 {
    public static void main(String[] args) {
        //ESCRIBIR OBJETOS
        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Diego", 25));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clientes.dat"))){
            for (Persona persona : personas) {
                out.writeObject(persona);
            }
            public void escribirObjeto(File fichero, ArrayList<Cliente> datos) 

        } catch (IOException e) {
            
        } 
    }
}
