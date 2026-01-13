package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/persona")
public class GestionaPersona {
    static ArrayList<Persona> personas = new ArrayList<Persona>();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ArrayList<Persona> leer(){
        Persona p = new Persona();
        p.setId(1);
        p.setCadena("Hugo");
        p.setCasado(false);
        p.setSexo("Hombre");
        personas.add(p);
        return personas;
    }
    
}
