import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {
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
