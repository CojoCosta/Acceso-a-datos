package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportista")
public class EjerciciosDeportista {
    private static final String url = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String user = "root";
    private static final String password = "";

    ArrayList<Deportista> deportistas = new ArrayList<>();

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
                    deportista = new Deportista(rs.getInt("id"), rs.getString("nombre"), rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero"));
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
            try (Connection conexion = DriverManager.getConnection(url, user, password);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE genero LIKE 'masculino'")) {
                while (rs.next()) {
                    deportistasHombres.add(new Deportista(rs.getInt("id"), rs.getString("nombre"),
                            rs.getBoolean("activo"), rs.getString("deporte"), rs.getString("genero")));
                }
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistasHombres) {
                };
                return Response.ok(entity).build();
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

    // Ejercicio 4.8 Femeninos (/femeninos): Lista los deportistas femeninos
    @GET
    @Path("/masculinos")
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
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistasHembras) {
                };
                return Response.ok(entity).build();
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
    public Response deportistasPorGenero() {
        ArrayList<Response> deportistasHombres = new ArrayList<>();
        deportistasHombres.add(deportistasMasculinos());
        ArrayList<Response> deportistasMujeres = new ArrayList<>();
        deportistasHombres.add(deportistasFemeninos());
        ArrayList<ArrayList<Response>> mascYFem = new ArrayList<>();
        mascYFem.add(deportistasMujeres);
        mascYFem.add(deportistasHombres);
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, user, password)) {
                Statement st = conexion.createStatement();
                ResultSet rsMasc = st.executeQuery("SELECT * FROM deportistas WHERE genero LIKE 'masculino'");
                ResultSet rsFem = st.executeQuery("SELECT * FROM deportistas WHERE genero LIKE 'femenino'");
                while (rsMasc.next()) {
                    
                }
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }

}
