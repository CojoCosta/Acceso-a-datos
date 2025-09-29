import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejercicio10 {
        public static Depart añadirDepartamento(String nombreDepart, int numeroDepart) {
        return new Depart(nombreDepart, numeroDepart);
    }

    public static Persona añadirPersona(String nombre, int edad) {
        return new Persona(nombre, edad);
    }

    public static void añadirObjetos(ArrayList<Object> objetos, Object obj) {
        if (obj.getClass() == Persona.class || obj.getClass() == Depart.class) {
            objetos.add(obj);
        }
    }

    public static void escribirArchivo(File fichero, ArrayList<Object> objetos) {
        try (FileOutputStream fos = new FileOutputStream(fichero);
                ObjectOutputStream out = new ObjectOutputStream(fos)) {
            for (Object obj : objetos) {
                out.writeObject(obj);
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<Object> consultarArchivo(File fichero) {
        ArrayList<Object> objetos = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fichero); ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (true) {
                objetos.add(ois.readObject());
            }
        } catch (Exception e) {
        }
        return objetos;
    }

    public static void eliminarObjeto(ArrayList<Object> objetos, Object obj) {
        for (int i = 0; i < objetos.size(); i++) {
            if (obj instanceof Persona && objetos.get(i) instanceof Persona) {
                if (((Persona) obj).getNombre().equals(((Persona) objetos.get(i)).getNombre())) {
                    objetos.remove(i);
                    i--;
                }
            } else if (obj instanceof Depart && objetos.get(i) instanceof Depart) {
                if (((Depart) obj).getCodigo() == ((Depart) objetos.get(i)).getCodigo()) {
                    objetos.remove(i);
                    i--;
                }
            }
        }
    }
    public static void main(String[] args) {
        File fichero = new File("Ejercicio10.dat");
        ArrayList<Object> objetos = consultarArchivo(fichero);

        añadirObjetos(objetos, añadirPersona("Iago", 26));
        añadirObjetos(objetos, añadirDepartamento("2ºDAM", 555));

        escribirArchivo(fichero, objetos);

        System.out.println("Lista primaria");
        for (Object object : objetos) {
            System.out.println(object.toString());
        }

        eliminarObjeto(objetos, new Persona("Iago", 26));
        escribirArchivo(fichero, objetos);

        System.out.println("Lista tras borrar");
        for (Object object : objetos) {
            System.out.println(object.toString());
        }
    }
}
