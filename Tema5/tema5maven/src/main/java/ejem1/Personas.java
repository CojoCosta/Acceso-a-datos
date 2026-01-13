package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas = new ArrayList<Persona>();

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void guardarPersonas(Persona persona){
        personas.add(persona);
    }


    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Persona> listarPersonas(){
        return personas;
    }

    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Persona verPersona(@PathParam("cadena")String cadena){
        for (Persona persona : personas) {
            if (persona.getNombre().equals(cadena)) {
                return persona;
            }
        }
        return null;
    }

    @GET
    @Path("/buscar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Persona> ver(@QueryParam("cadena")String cadena){
        ArrayList<Persona> personasConCadena = new ArrayList<>();
        String cadenaLower = cadena.toLowerCase();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(cadenaLower)) {
                personasConCadena.add(p);
            }
        }
        return personasConCadena;
    }


}
