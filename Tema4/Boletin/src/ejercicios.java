
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

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

    public static void ejercicio9A() throws SQLException {
        String nombre_driver, version_driver, url_conexion, nombre_sgbd, version_sgbd, palabras_sgbd;
        DatabaseMetaData dbmd = conexion.getMetaData();
        nombre_driver = dbmd.getDriverName();
        version_driver = dbmd.getDriverVersion();
        url_conexion = dbmd.getURL();
        nombre_sgbd = dbmd.getDatabaseProductName();
        version_sgbd = dbmd.getDatabaseProductVersion();
        palabras_sgbd = dbmd.getSQLKeywords();
        System.out.printf(
                "Nombre driver: %s \nVersión driver: %s \nURL: %s \nNombre sgbd: %s \nVersion sgbd: %s \nPalabras reservadas: %s\n",
                nombre_driver, version_driver, url_conexion, nombre_sgbd, version_sgbd, palabras_sgbd);
    }

    public static void ejercicio9B() throws SQLException {
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet catalogo = dbmd.getCatalogs();
        while (catalogo.next()) {
            System.out.printf("%s\n", catalogo.getString("TABLE_CAT"));
        }
    }

    public static void ejercicio9C() throws SQLException {
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet rs = dbmd.getTables("add", null, null, null);
        while (rs.next()) {
            System.out.printf("Nombre de la tabla: %s \nTipo de tabla: %s\n\n", rs.getString("TABLE_NAME"),
                    rs.getString("TABLE_TYPE"));
        }
    }

    public static void ejercicio9D() throws SQLException {
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet rs = dbmd.getTables("add", null, null, null);
        while (rs.next()) {
            if (rs.getString("TABLE_TYPE").equals("VIEW")) {
                System.out.printf("Nombre de la tabla: %s \nTipo de tabla: %s\n\n", rs.getString("TABLE_NAME"),
                        rs.getString("TABLE_TYPE"));
            }
        }
    }

    public static void ejercicio9E() throws SQLException {
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet catalogo = dbmd.getCatalogs();
        ResultSet rs = dbmd.getTables("add", null, null, null);
        while (rs.next()) {
            System.out.printf("Nombre de la tabla: %s \nTipo de tabla: %s\n\n", rs.getString("TABLE_NAME"),
                    rs.getString("TABLE_TYPE"));
        }
        while (catalogo.next()) {
            System.out.printf("%s\n", catalogo.getString("TABLE_CAT"));
        }
    }

    public static void ejercicio9F() throws SQLException {
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet rs = dbmd.getProcedures("add", null, null);
        while (rs.next()) {
            System.out.println(rs.getString("PROCEDURE_NAME"));
        }
    }

    public static void ejercicio9G() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getColumns("add", null, "a%", null);
            while (rs.next()) {
                System.out.printf(
                        "Posicion:%s - Tabla:%s - Nombre Columna:%s - TipoDato:%s - TamañoCol:%s - Nulos:%s - Autoincrementado: %s\n",
                        rs.getString("ORDINAL_POSITION"), rs.getString("TABLE_NAME"),
                        rs.getString("COLUMN_NAME"), rs.getString("TYPE_NAME"), rs.getString("COLUMN_SIZE"),
                        rs.getString("IS_NULLABLE"), rs.getString("IS_AUTOINCREMENT"));
            }
        } catch (SQLException e) {
        }
    }

    public static void ejercicio9H() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet rs = dbmd.getPrimaryKeys("add", null, null);
            System.out.println("Claves Primarias:");
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio9H2() {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            String nomTabla = "";
            ResultSet rsTablas = dbmd.getTables("add", null, null, null);
            while (rsTablas.next()) {
                nomTabla = rsTablas.getString("TABLE_NAME");
                ResultSet rs = dbmd.getExportedKeys("add", null, nomTabla); // Esta linea da error
                System.out.println("Claves Foraneas");
                while (rs.next()) {
                    System.out.println(rs.getString("FKCOLUMN_NAME"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio10() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "select *, nombre as non from alumnos";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.printf("%-3s\t%-20s\t%-20s\t%-25s\t%-25s\t%-25s\n", "NUM", "NOMBRE COL", "ALIAS COL",
                    "TIPO DATO", "AUTOINCREMENT", "NULLABLE");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.printf(
                        "%-3d\tNombre: %-15s\tAlias: %-15s\tTipo de dato: %-20s\tEs autoincrement: %-20s\tEs nullable: %-20s\n",
                        i, rsmd.getColumnName(i), rsmd.getColumnLabel(i), rsmd.getColumnTypeName(i),
                        rsmd.isAutoIncrement(i), rsmd.isNullable(i));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    public static void ejercicio12() {
        try {
            conexion.setAutoCommit(false);
            Statement st = conexion.createStatement();
            st.executeUpdate(
                    "INSERT INTO alumnos (nombre, apellidos, altura, curso) VALUES ('Pablo', 'Santana Alonso', 170, 2)");
            System.out.println("Inserción relizada correctamente");
            conexion.commit();
            System.out.println("Commit realizado");
        } catch (SQLException e) {
            System.out.println("Se ha producido un error en una consulta: " + e.getLocalizedMessage());
            try {
                if (conexion != null) {
                    System.out.println("Se ha producido un error, deshaciendo cambios...");
                    conexion.rollback();
                }
            } catch (SQLException i) {
                System.out.println("Error en el rollback: " + i.getLocalizedMessage());
            }
        }
    }

    public static void ejercicio12B() {
        try {
            conexion.setAutoCommit(false);
            Statement st = conexion.createStatement();
            st.executeUpdate(
                    "INSERT INTO alumnos (nombre, apellidos, altura, curso) VALUES ('Pablo', 'Santana Alonso', 170, 2)");
            System.out.println("Inserción relizada correctamente");
            conexion.commit();
            System.out.println("Commit realizado");
            st.close();
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException i) {
                System.out.println("Error RollBack");
            }
        }
    }

    public static void ejercicio13() {
        try (Statement st = conexion.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM imagenes WHERE nombre = 'escritor1.jpg'");
            InputStream is = rs.getBinaryStream("imagen");
            try (FileOutputStream fos = new FileOutputStream("C:\\imagenes\\imagenes.dat")) {
                int i;
                byte[] buffer = new byte[1000];
                while ((i = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, i);
                }
                is.close();
            } catch (IOException e) {
                System.out.println("Error de archivo");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio13B() throws FileNotFoundException {
        try (Statement st = conexion.createStatement()) {
            try {
                FileInputStream fis = new FileInputStream("C:\\imagenes\\imagenes.dat");
                fis.read();
                String consulta = "INSERT INTO imagenes VALUES (?,?)";
                ps = conexion.prepareStatement(consulta);
                ps.setString(1, "Nuevo_Nombre");
                ps.setBinaryStream(2, fis, 64);
            } catch (IOException e) {
                System.out.println("Error de archivo");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio15() {
        try {
            int numeroAula = 0;
            String nombreAula = "";
            int puestos = 0;
            CallableStatement cs = conexion.prepareCall("CALL getAulas(?,?)");
            cs.setInt(1, 10);
            cs.setString(2, "o");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                numeroAula = rs.getInt("numero");
                nombreAula = rs.getString("nombreAula");
                puestos = rs.getInt("puestos");
                System.out.printf("Numero:%2d, Nombre:%s, Puestos:%2d\n", numeroAula, nombreAula, puestos);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio15B() {
        try {
            CallableStatement cs = conexion.prepareCall("{ ? = CALL SUMA() }");
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();
            int resultado = cs.getInt(1);
            System.out.println("Resultado:" + resultado);

        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    public static void ejercicio16(String textoBuscado, String bd) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tablas = dbmd.getTables(bd, null, "%", new String[] { "TABLE" });
            while (tablas.next()) {
                String nombreTabla = tablas.getString("TABLE_NAME");
                ResultSet columnas = dbmd.getColumns(bd, null, nombreTabla, "%");
                while (columnas.next()) {
                    String nombreColumna = columnas.getString("COLUMN_NAME");
                    String tipo = columnas.getString("TYPE_NAME");
                    if (tipo.equalsIgnoreCase("CHAR") || tipo.equalsIgnoreCase("VARCHAR")) {
                        String sql = "SELECT " + nombreColumna +
                                " FROM " + nombreTabla +
                                " WHERE " + nombreColumna + " LIKE ?";
                        PreparedStatement ps = conexion.prepareStatement(sql);
                        ps.setString(1, "%" + textoBuscado + "%");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String valor = rs.getString(1);
                            System.out.println("BD: " + bd + " | Tabla: " + nombreTabla + " | Columna: " + nombreColumna
                                    + " | Valor: " + valor);
                        }
                        rs.close();
                        ps.close();
                    }
                }
                columnas.close();
            }
            tablas.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        abrirConexion("add", "localhost", "root", "");
        System.out.println("-----------EJERCICIO 1----------------");
        // consultarDatos("a");
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
        // ejercicio9A();
        // ejercicio9B();
        // ejercicio9C();
        // ejercicio9D();
        // ejercicio9E();
        // ejercicio9F();
        // ejercicio9G();
        // ejercicio9H();
        System.out.println("-----------EJERCICIO 10----------------");
        // ejercicio10();
        // cerrarConexion();
        System.out.println("-----------EJERCICIO 12----------------");
        // ejercicio12();
        // ejercicio12B();
        System.out.println("-----------EJERCICIO 13----------------");
        // ejercicio13();
        // ejercicio13B();
        System.out.println("-----------EJERCICIO 15----------------");
        // ejercicio15();
        // ejercicio15B();
        System.out.println("-----------EJERCICIO 16----------------");
        // ejercicio16("e", "add");
    }
}
