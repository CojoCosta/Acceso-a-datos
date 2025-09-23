import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio9 {
    static public int codigo;
    static public String nombre;
    static public double altura; 

    public static void altaAlumnos()throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Codigo: ");
        try {
            codigo = sc.nextInt();
        } catch (IllegalArgumentException e) {
            System.err.println("numero entero");
        }
        sc.nextLine();
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Altura: ");
        try {
            altura = sc.nextDouble();
        } catch (IllegalArgumentException e) {
            System.err.println("numero decimal");
        }
        sc.nextLine();

        try (FileWriter fw = new FileWriter(new File("alumnos.txt"))){
            fw.write(String.format("Codigo del alumno: %d \nNombre del alumno: %s \nAltura del alumno: %.2f",codigo, nombre, altura));
        }
    }

    public static void consultarAlumnos()throws IOException{

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
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
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
