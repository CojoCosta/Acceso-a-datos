import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio9 {
    static public int codigo;
    static public String nombre;
    static public double altura;
    static public Alumno datos;
    static public ArrayList <Alumno> alumnos = new ArrayList<>();
    static public FileOutputStream fos = null;
    static public FileInputStream fis = null;

    public static void altaAlumnos()throws IOException{
        datos = new Alumno();
        fos = new FileOutputStream(new File("alumnos.dat"));
        try (DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(datos.getCodigo());
            dos.writeUTF(datos.getNombre());
            dos.writeFloat(datos.getAltura());
        }
        alumnos.add(datos);
    }
    
    public static void consultarAlumnos()throws IOException{
        fis = new FileInputStream("alumnos.dat");
        try (DataInputStream dis = new DataInputStream(fis)){
            for (Alumno alumno : alumnos) {
                System.out.println(dis.readInt());
                System.out.println(dis.readUTF());
                System.out.println(dis.readFloat());
            }
        }
    }
    
    public static void modificarAlumnos()throws IOException{
        
    }
    
    public static void eliminarAlumnos()throws IOException{

    }
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("1.- Dar de alta alumnos");
            System.out.println("2.- Consultar alumnos");
            System.out.println("3.- Modificar alumnos");
            System.out.println("4.- Borrar alumnos");
            System.out.println("5.- Salir");
            try {
                option = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
            }
            switch (option) {
                case 1:
                    altaAlumnos();                    
                    break;
                case 2:
                    consultarAlumnos();
                    break;
                case 3:
                    modificarAlumnos();
                    break;
                case 4:
                    eliminarAlumnos();
                    break;
                case 5:
                    System.out.println("Saliste");
                    break;
            
                default:
                System.out.println("Del 1 al 5");
                    break;
            }
        } while (option != 5);
    }
}
