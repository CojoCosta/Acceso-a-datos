
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    public static void consultarDatos(String a) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("SELECT * FROM alumnos Where nombre LIKE '%%%s%%'", a);
            ResultSet resultadoConsulta = st.executeQuery(consulta);
            while (resultadoConsulta.next()) {
                System.out.printf("%d \t %s\n", resultadoConsulta.getInt("codigo"),
                        resultadoConsulta.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void altaAlumno(String nombre, String apellidos, int altura, int aula) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format(
                    "INSERT INTO alumnos (nombre, apellidos, altura, aula) VALUES ('%s', '%s', %d, %d)", nombre,
                    apellidos, altura, aula);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void altaAsignatura(String nombre) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("INSERT INTO asignaturas (nombre) VALUES ('%s')", nombre);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void bajaAlumno(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM alumnos WHERE codigo = %d", codigo);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void bajaAsignatura(int codigo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("DELETE FROM asignaturas WHERE cod = %d", codigo);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void modificarAlumno(String nombreViejo, String nombreNuevo) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("UPDATE alumnos SET nombre = '%s' WHERE nombre = '%s'", nombreNuevo,
                    nombreViejo);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void modificarAsignatura(String asignaturaVieja, String asignaturaNueva) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format("UPDATE asignaturas SET nombre = '%s' WHERE nombre = '%s'", asignaturaNueva,
                    asignaturaVieja);
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void aulasConAlumnos() {
        try (Statement st = conexion.createStatement()) {
            String consulta1 = "SELECT alumnos.nombre,  asignaturas.nombre FROM alumnos JOIN asignaturas JOIN notas on asignaturas.COD = notas.asignatura and alumnos.codigo = notas.alumno";
            ResultSet resultado = st.executeQuery(consulta1);
            System.out.printf("%-10s\t%-30s\n", "Nombres:", "Asignaturas:");
            while (resultado.next()) {
                System.out.printf("%-10s\t%-30s\n", resultado.getString("alumnos.nombre"),
                        resultado.getString("asignaturas.nombre"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void aprobados() {
        try (Statement st = conexion.createStatement()) {
            String consulta1 = "SELECT asignaturas.nombre , alumnos.nombre, notas.nota FROM asignaturas, alumnos, notas WHERE asignaturas.COD = alumnos.codigo and notas.asignatura = asignaturas.COD AND notas.nota >= 5";
            ResultSet resultado = st.executeQuery(consulta1);
            System.out.printf("%-10s\t%-30s\t%-5s\n", "Nombres:", "Asignaturas:", "Notas:");
            while (resultado.next()) {
                System.out.printf("%-10s\t%-30s\t%-5d\n", resultado.getString("alumnos.nombre"),
                        resultado.getString("asignaturas.nombre"), resultado.getInt("notas.nota"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void asignaturaSinAlumnos() {
        try (Statement st = conexion.createStatement()) {
            String consulta1 = "SELECT * FROM asignaturas WHERE NOT EXISTS (SELECT * FROM notas WHERE notas.asignatura = asignaturas.COD)";
            ResultSet resultado = st.executeQuery(consulta1);
            System.out.printf("%-10s ", "Asignatura:");
            while (resultado.next()) {
                System.out.printf("%-10s\n", resultado.getString("asignaturas.nombre"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void nombreYAltura(String patron, int altura) {
        try (Statement st = conexion.createStatement()) {
            String consulta = String.format(
                    "SELECT nombre, altura FROM alumnos WHERE nombre like \"%%%s%%\" AND altura > %d", patron, altura);

            ResultSet resultado = st.executeQuery(consulta);
            while (resultado.next()) {
                System.out.printf("Nombre: %-10s\t Altura: %-5d\n", resultado.getString("alumnos.nombre"),
                        resultado.getInt("alumnos.altura"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    private static PreparedStatement ps = null;

    public static void nombreYAltura2(String patron, int altura) throws SQLException {
        String consulta = String.format("SELECT nombre, altura FROM alumnos WHERE nombre like ? AND altura > ?");
        ps = conexion.prepareStatement(consulta);
        ps.setString(1, patron);
        ps.setInt(2, altura);
        ResultSet resultado = ps.executeQuery();
        while (resultado.next()) {
            System.out.printf("Nombre: %-10s\t Altura: %-5d\n", resultado.getString("alumnos.nombre"),
                    resultado.getInt("alumnos.altura"));
        }
    }

    public static void añadirColumna(String nombreTabla, String nombreColumna, String tipoDato, String propiedades) {
        try (Statement st = conexion.createStatement()) {
            String consulta1 = String.format("ALTER TABLE %s ADD %s %s %s", nombreTabla, nombreColumna, tipoDato,
                    propiedades);
            int resultado = st.executeUpdate(consulta1);
            System.out.println(resultado);
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    //Ej 10
    public static void obtenerDatos() {
        try (Statement st = conexion.createStatement()){
            String consulta = "select *, nombre as non from alumnos";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-3s\t%-25s\t%-25s\t%-25s\t%25s\t%25s\n","NUM", "NOMBRE COL", "ALIAS COL", "TIPO DATO", "AUTOINCREMENT", "NULLABLE");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf("%-3d\tNombre: %-20s\tAlias: %-20s\tTipo de dato: %-20s\tEs autoincrement: %-20s\tEs nullable: %-20s\n", i, rsmd.getColumnName(i),rsmd.getColumnLabel(i) ,rsmd.getColumnTypeName(i),rsmd.getColumnTypeName(i), rsmd.isAutoIncrement(i),rsmd.isNullable(i));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");        
        }
    }

    public static void main(String[] args) throws SQLException {
        abrirConexion("add", "localhost", "root", "");
        System.out.println("-----------EJERCICIO 1----------------");
        consultarDatos("a");
        System.out.println("-----------EJERCICIO 2----------------");
        // altaAlumno("Carlos", "Italiani", 187, 20);
        System.out.println("-----------EJERCICIO 2.1----------------");
        // altaAsignatura("Acceso a datos");
        System.out.println("-----------EJERCICIO 3----------------");
        // bajaAlumno(10);
        System.out.println("-----------EJERCICIO 3.1----------------");
        // bajaAsignatura(9);
        System.out.println("-----------EJERCICIO 4----------------");
        // modificarAlumno("Frank","Franco");
        System.out.println("-----------EJERCICIO 4.1----------------");
        // modificarAsignatura("FOL","Empresa");
        System.out.println("-----------EJERCICIO 5----------------");
        // aulasConAlumnos();
        System.out.println("-----------EJERCICIO 5.1----------------");
        // aprobados();
        System.out.println("-----------EJERCICIO 5.2----------------");
        // asignaturaSinAlumnos();
        System.out.println("-----------EJERCICIO 6----------------");
        // nombreYAltura("a", 170);
        System.out.println("-----------EJERCICIO 6.1----------------");
        // nombreYAltura2("%a%", 170);
        System.out.println("-----------EJERCICIO 7----------------");
        // long inicio = System.nanoTime();
        // for (int i = 0; i < 1000; i++) {
        // nombreYAltura2("%a%", 170);
        // }
        // long fin = System.nanoTime();
        // System.out.println("No preparada "+(fin - inicio));
        // long inicio2 = System.nanoTime();
        // for (int i = 0; i < 1000; i++) {
        // nombreYAltura2("%a%", 170);
        // }
        // long fin2 = System.nanoTime();
        // System.out.println("Preparada "+(fin2 - inicio2));
        System.out.println("-----------EJERCICIO 8----------------");
        // añadirColumna("imagenes", "curso", "TINYINT", "");
        System.out.println("-----------EJERCICIO 9----------------");
        System.out.println("-----------EJERCICIO 10----------------");
        obtenerDatos();
        cerrarConexion();
    }
    // try (Statement st = conexion.createStatement()){

    // } catch (SQLException e) {
    // System.out.println("ERROR");
    // }
}
