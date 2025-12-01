
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ejercicios {
    private static Connection conexion;

    public static void abrirConexion(String bd, String servidor, String usuario, String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            // Establecemos la conexión con la BD
            conexion = DriverManager.getConnection(url, usuario, password);
            if (conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public static void consultarDatos(String a){
        try (Statement st = conexion.createStatement()){
            String consulta = String.format("SELECT * FROM alumnos Where nombre LIKE '%%%s%%'", a);
            ResultSet resultadoConsulta = st.executeQuery(consulta);
            while (resultadoConsulta.next()) {
                System.out.printf("%d \t %s\n",resultadoConsulta.getInt("codigo"), resultadoConsulta.getString("nombre") );
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void altaAlumno(String nombre, String apellidos, int altura, int aula){
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("INSERT INTO jugadores_celta (nombre, apellidos, altura, aula) Values (%s, %s, %d, %d)",nombre, apellidos,altura, aula);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }
    public static void altaAsignatura(String nombre){
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("INSERT INTO jugadores_celta (nombre) Values (%s)",nombre);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void bajaAlumno(int codigo){
        
    }

    public static void bajaAsignatura(int codigo){

    }
    
    public static void main(String[] args) throws SQLException {
        abrirConexion("add", "localhost", "root", "");
        System.out.println("-----------EJERCICIO 1----------------");
        consultarDatos("a");
        System.out.println("-----------EJERCICIO 2----------------");
        altaAlumno("Carlos", "Italiani", 187, 20);
        System.out.println("-----------EJERCICIO 2.1----------------");
        altaAsignatura("Acceso a datos");
        System.out.println("-----------EJERCICIO 3----------------");
        bajaAlumno(10);
        System.out.println("-----------EJERCICIO 3.1----------------");
        bajaAsignatura(9);
        System.out.println("-----------EJERCICIO 4----------------");

        cerrarConexion();
    }
    // Ejercicios: 1.- Nombre y edad >30 años; 2.- Insertar Manuel ; 3.- Actualizar Guaita por Radu 
}
