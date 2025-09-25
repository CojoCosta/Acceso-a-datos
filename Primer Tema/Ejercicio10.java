import java.util.Scanner;

public class Ejercicio10 {
    public static void añadirPersona(){

    }

    public static void añadirDepartamento(){
        
    }

    public static void consulatarDatos(){

    }

    public static void borrarPersona(){

    }

    public static void borrarDepartamento(){

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            
            System.out.println("1.- Inclusion de personas");
            System.out.println("2.- Inclusion de departamentos");
            System.out.println("3.- Consultar datos");
            System.out.println("4.- Borrar persona");
            System.out.println("5.- Borrar departamento");
            System.out.println("6.- SAlir");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    añadirPersona();
                    break;

                case 2:
                    añadirDepartamento();
                    break;

                case 3:
                    consulatarDatos();
                    break;

                case 4:
                    borrarPersona();
                    break;

                case 5:
                    borrarDepartamento();
                    break;

                case 6:
                    System.out.println("Saliste");
                    break;

                default:
                    System.out.println("Elige una opcion");
                    break;
            }
        } while (option != 6);
    }
}
