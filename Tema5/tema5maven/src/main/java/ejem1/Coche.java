package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/coches")
public class Coche {
    static ArrayList<Car> coches = new ArrayList<Car>();
    @DefaultValue("valor por defecto")
    @QueryParam("valor")
    String text;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCar(Car coche) {
        coches.add(coche); // Se añade el coche a la lista
        return Response.ok(coche).build(); // Se devuelve el coche
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Car> getXML() {
        Car c = new Car(); // Se crea un coche y se inicializan sus param.
        c.setMarca("Ford");
        c.setModelo("Focus");
        coches.add(c); // Se añade el coche a la lista
        return coches;
    }

    @GET
    @Path("/{modelo}")
    public String quienEres(@PathParam("modelo") String name) {
        return "Hola, soy un " + name;
    }

    @GET
    @Path("coche")
    public String marcaYModelo(@QueryParam("marca") String marca,
            @QueryParam("modelo") String mo) {
        return "La marca es " + marca + " y el modelo " + mo;
        // http://localhost:8080/tema5maven/rest/coches/coche?marca=nissan&modelo=pixo
    }

    @GET
    @Path("cocheDefecto")
    public String marcaYModeloD(
            @DefaultValue("Citroen") @QueryParam("marca") String marca,
            @DefaultValue("Saxo") @QueryParam("modelo") String mo) {
        return "La marca es " + marca + " y el modelo " + mo;
    }
    //http://localhost:8080/tema5maven/rest/coches/cocheDefecto
}