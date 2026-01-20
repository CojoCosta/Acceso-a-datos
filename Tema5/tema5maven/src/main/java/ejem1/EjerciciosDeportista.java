package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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

    // Ejercicio 4.2
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
                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(deportistas) {};
                return Response.ok(entity).build();
                // return Response.ok(deportistas).build(); //Esto solo muestra json
            } catch (Exception e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error").build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No encuentra el driver").build();
        }
    }
    
    // Ejercicio 4.3
        
}
