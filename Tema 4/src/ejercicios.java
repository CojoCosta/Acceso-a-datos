
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

    public static void consultarJugadores(){
        try (Statement st = conexion.createStatement()){
            String consulta = "SELECT * FROM jugadores_celta";
            ResultSet resultadoConsulta = st.executeQuery(consulta);
            while (resultadoConsulta.next()) {
                System.out.println(resultadoConsulta.getInt("dorsal") + "\t" +resultadoConsulta.getString(2) );
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }
    public static void borrarJugador(int dorsal){
        try (Statement st = conexion.createStatement()){
            String consulta = "DELETE FROM jugadores_celta WHERE dorsal=" + dorsal;
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void nombreyedad(){
        try (Statement st = conexion.createStatement()){
            String consulta = "SELECT nombre, edad FROM jugadores_celta WHERE edad >= 30";
            ResultSet resultadoConsulta = st.executeQuery(consulta);
            while (resultadoConsulta.next()) {
                System.out.println(resultadoConsulta.getString("nombre") + "\t" +resultadoConsulta.getInt("edad") );
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void insertarManuel(){
        try (Statement st = conexion.createStatement()) {
            String consulta = "INSERT INTO jugadores_celta (dorsal, nombre, posicion, edad, nacionalidad, convocado, partidos_jugados, goles, minutos_jugados) Values (99, 'Manuel Hay', 'Banquillo', 19, 'Peruano', 0, 0, 0, 0)";
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void cambiarAGuaita(){
        try (Statement st = conexion.createStatement()){
            String consulta = "UPDATE jugadores_celta SET nombre = 'RADU' WHERE nombre = 'Vicente Guaita'";
            int filaAfectada = st.executeUpdate(consulta);
            System.out.println(filaAfectada); 
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }
    private static PreparedStatement ps = null;
    public static void consultarPreparado(int dorsal) throws SQLException{
        String consulta = "Select * FROM jugadores_celta WHERE dorsal = ?";
        ps = conexion.prepareStatement(consulta);
        ps.setInt(1, dorsal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" +rs.getString(2));
        }
    }
    public static void main(String[] args) throws SQLException {
        abrirConexion("celta", "localhost", "root", "");

        // consultarJugadores();
        // borrarJugador(1);
        // nombreyedad();
        // insertarManuel();
        // cambiarAGuaita();
        consultarPreparado(13);
        cerrarConexion();
    }
    // Ejercicios: 1.- Nombre y edad >30 años; 2.- Insertar Manuel ; 3.- Actualizar Guaita por Radu 
}
