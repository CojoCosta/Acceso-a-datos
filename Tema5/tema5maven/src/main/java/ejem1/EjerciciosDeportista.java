package ejem1;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportista")
public class EjerciciosDeportista {
    private static final String url = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String user = "root";
    private static final String password = "";

    ArrayList<Deportista> deportistas = new ArrayList<>();

    // Subir una consulta
    @POST
    @Path("/android")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response subirDeportistaAndroid(Deportista deportista) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            st.executeUpdate(String.format("INSERT INTO deportistas (nombre, deporte) VALUES ('%s', '%s')",
                    deportista.getNombre(), deportista.getDeporte()));
            return Response.ok("Subido correctamente").build(); // Esto solo muestra json
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
        }
    }

    // Ejercicio 4.2 Todos (/): devuelve un listado con todos los deportistas del
    // sistema.
    @GET()
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response listaDeportistas() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas")) {
                while (rs.next()) {
                    deportistas.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("deporte"), rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistas) {
                };
                return Response.ok(entity).build();
                // return Response.ok(deportistas).build(); //Esto solo muestra json
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.3 Buscar jugador (/{id}): devuelve la información relativa al
    // deportista id
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistaPorId(@PathParam("id") int id) {
        Deportista deportista = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(String.format("SELECT * FROM deportistas WHERE id = %d", id))) {
                while (rs.next()) {
                    deportista = new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                            rs.getString("deporte"), rs.getString("genero"));
                }
                return Response.ok(deportista).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.4 Por deporte (/deporte/{nombreDeporte}): Lista los deportistas
    // de un deporte.
    @GET
    @Path("/deporte/{nombreDeporte}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistaPorDeporte(@PathParam("nombreDeporte") String deporte) {
        ArrayList<Deportista> deportistasPorDeporte = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery(
                            String.format("SELECT * FROM deportistas WHERE deporte LIKE '%s'", deporte))) {
                while (rs.next()) {
                    deportistasPorDeporte.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistasPorDeporte) {
                };
                return Response.ok(entity).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.5 Activos (/activos): Lista los deportistas activos
    @GET
    @Path("/activos")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistasActivos() {
        ArrayList<Deportista> deportistasActivos = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE activo = true")) {
                while (rs.next()) {
                    deportistasActivos.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistasActivos) {
                };
                return Response.ok(entity).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.6 Retirados (/retirados): Lista los deportistas retirados.
    @GET
    @Path("/retirados")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistasRetirados() {
        ArrayList<Deportista> deportistasRetirados = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE activo = false")) {
                while (rs.next()) {
                    deportistasRetirados.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistasRetirados) {
                };
                return Response.ok(entity).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.7 Masculinos (/masculinos): Lista los deportistas masculinos.
    @GET
    @Path("/masculinos")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistasMasculinos() {
        ArrayList<Deportista> deportistasHombres = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE genero LIKE 'masculino'");
            while (rs.next()) {
                deportistasHombres.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                        rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
            }
            return Response.ok(deportistasHombres).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // // Ejercicio 4.8 Femeninos (/femeninos): Lista los deportistas femeninos
    @GET
    @Path("/femeninos")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistasFemeninos() {
        ArrayList<Deportista> deportistasHembras = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE genero LIKE 'femenino'")) {
                while (rs.next()) {
                    deportistasHembras.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
                }
                return Response.ok(deportistasHembras).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.9 Deportes por genero (/xg): Lista un array con dos elementos:
    // uno con todos los deportistas masculinos y otro con todos los deportistas
    // femeninos.
    @GET
    @Path("/xg")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportistasPorGenero() throws ClassNotFoundException {
        ArrayList<Response> masculino = new ArrayList<>();
        masculino.add(deportistasMasculinos());

        ArrayList<Response> femenino = new ArrayList<>();
        femenino.add(deportistasFemeninos());

        ArrayList<ArrayList<Response>> general = new ArrayList<>();
        general.add(masculino);
        general.add(femenino);
        return Response.ok(general).build();
    }

    // Ejercicio 4.10 Activos por deporte (/deporte/{nombreDeporte}/activos): Lista
    // los deportistas activos de un deporte.
    @GET
    @Path("/deporte/{nombreDeporte}/activos")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response activosPorDeporte(@PathParam("nombreDeporte") String nombreDeporte) {
        ArrayList<Deportista> activosPorDeporte = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String
                    .format("SELECT * FROM deportistas WHERE activo = true and deporte like '%s'", nombreDeporte));
            while (rs.next()) {
                activosPorDeporte.add(new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"),
                        rs.getString("deporte"), rs.getString("genero")));
            }
            return Response.ok(activosPorDeporte).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.11 Contar deportistas (/sdepor): Cuenta el número de deportistas
    // distintos.
    @GET
    @Path("/sdepor")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response numeroDeDeportistas() {
        int numDeportistas = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
        try (Connection conexion = DriverManager.getConnection(url, user, password)) {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM deportistas");
            while (rs.next()) {
                numDeportistas++;
            }
            return Response.ok(numDeportistas).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.12 Lista deportes (/deportes): Lista los deportes existentes
    // ordenados alfabéticamente sin repeticiones.
    @GET
    @Path("/deportes")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deportes() {
        ArrayList<String> deportes = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA")
                    .build();
        }
        try (Connection conexion = DriverManager.getConnection(url, user, password);
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT DISTINCT(deporte) FROM deportistas ORDER BY deporte;")) {
            while (rs.next()) {
                deportes.add(rs.getString("deporte"));
            }
            return Response.ok(deportes).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.13. Crear deportista (/): Añade un deportista en el sistema.
    @POST
    @Path("/nuevo")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crearDeportista(Deportista deportista) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
        try {
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            st.executeUpdate(String.format("INSERT INTO deportistas (nombre, activo, deporte, genero) VALUES ('%s', '%s', '%s', '%s')", deportista.getNombre(), deportista.getActivo(), deportista.getDeporte(), deportista.getGenero()));
            return Response.ok("Subido correctamente").build(); // Esto solo muestra json
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
        }
    }

    //Ejercicio 4.14. Crear deportista formulario (/): Añade un deportista mediante un formulario
    @POST
    @Path("/form")
    @Consumes("application/x-www-form-urlencoded")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crearDeportistaForm(@FormParam("nombre") String nombre, @FormParam("activo") boolean activo, @FormParam("deporte") String deporte, @FormParam("genero") String genero) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
        try {
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            st.executeUpdate(String.format("INSERT INTO deportistas (nombre, activo, deporte, genero) VALUES ('%s', '%s', '%s', '%s')", nombre, activo, deporte, genero));
            return Response.ok("Subido correctamente").build(); // Esto solo muestra json
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
        }
    }

    //Ejercicio 4.15. Crear deportistas (/adds): crea deportistas en el sistema.
    @POST
    @Path("/adds")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response crearDeportistasistema(ArrayList<Deportista> deportistas) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
        try {
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            for (Deportista deportista : deportistas) {
                st.executeUpdate(String.format("INSERT INTO deportistas (nombre, activo, deporte, genero) VALUES ('%s', '%s', '%s', '%s')", deportista.getNombre(), deportista.getActivo(), deportista.getDeporte(), deportista.getGenero()));
            }
            return Response.ok("Subido correctamente").build(); // Esto solo muestra json
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
        }
    }

    //Ejercicio 4.16. Actualizar deportista (/): actualiza la información relativa a un deportista.
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response actualizarDeportista(Deportista deportista){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
        try {
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            st.executeUpdate(String.format("UPDATE deportistas SET nombre = '%s', activo = '%s', deporte = '%s', genero = '%s' WHERE id = %d", deportista.getNombre(), deportista.getActivo(), deportista.getDeporte(), deportista.getGenero(), deportista.getId()));
            return Response.ok(deportista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.17. Borrar deportista (/del/{id}): elimina la información relativa a un deportista id.
    @DELETE
    @Path("/del/{id}")
    public Response borrarPorId(@PathParam("id") int id){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
        try {
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            st.executeUpdate(String.format("DELETE FROM deportistas WHERE id = %d", id));
            return Response.ok("eliminado").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    //Ejercicio 4.18. Imagen deportista (/img/{id}/{num}): Muestra la imagen num del deportista id como image/jpg.
    @GET
    @Path("/img/{id}/{num}")
    @Produces("image/jpg")
    public Response imagenesPorIdNum(@PathParam("id") int id, @PathParam("num") int num){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
        try {
            String ruta = "";
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT nombre FROM imagenes WHERE id = %d AND nombre LIKE '%d_%d_%%'", id, id, num));
            while (rs.next()) {
                ruta = "C:\\imagenes\\imagenes\\" + rs.getString("nombre");
            }
            FileInputStream fis = new FileInputStream(new File(ruta));
            return Response.ok(fis).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver AAAAAAA").build();
        }
    }

    //Ejercicio 4.19. Imágenes deportistas (/img/{id}): Muestra el nombre y las imágenes del deportista id como html.
    @GET
    @Path("img/{id}")
    @Produces("image/jpg")
    public Response imagenesDeportistas(@PathParam("id") int id){
                try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
        try {
            String ruta = "";
            Connection conexion = DriverManager.getConnection(url, user, password);
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM deportistas JOIN imagenes USING (id) WHERE id = %d", id));
            while (rs.next()) {
                ruta = "C:\\imagenes\\imagenes\\" + rs.getString("imagenes.nombre");
            }
            FileInputStream fis = new FileInputStream(new File(ruta));
            return Response.ok(fis).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Esto sale porque: Iago no haces bien tu trabajo").build();
        }
    }
}