import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class getInfo {
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

    public static void getInfo(String db) {
        try {
            DatabaseMetaData dbmt = conexion.getMetaData();
            ResultSet tablas = dbmt.getTables(db, null, null, null);
            while (tablas.next()) {
                System.out.printf("Nombre de la tabla: %s\n",tablas.getString("TABLE_NAME"));
                ResultSet columnas = dbmt.getColumns(db, null, tablas.getString("TABLE_NAME"), null);
                while (columnas.next()) {
                    System.out.printf("Nombre columna: %s \nTipo de columna: %s \nTamaño de la columna: %d \nEs nullable: %s \nEs autoincrementada: %s\n\n", columnas.getString("COLUMN_NAME"),columnas.getString("TYPE_NAME"),columnas.getInt("COLUMN_SIZE"), columnas.getString("IS_NULLABLE"), columnas.getString("IS_AUTOINCREMENT"));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void getInfoConsulta(String db){
        try (Statement st = conexion.createStatement()){
            String consulta = "SELECT * FROM jugadores_celta";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-3s\t%-35s\t%-20s\n","NUM", "NAME", "TYPE");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-3d\tNombre columna: %-20s\tTipo de columna: %-20s\n", i, rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");        
        }
    }

    public static void main(String[] args) {
        abrirConexion("celta", "localhost", "root", "");
        // getInfo("celta");
        getInfoConsulta("celta");
        cerrarConexion();
    }
}
