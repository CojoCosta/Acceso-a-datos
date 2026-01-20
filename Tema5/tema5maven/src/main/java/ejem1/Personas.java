package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas = new ArrayList<Persona>();
    
    //Ej 3.1
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void guardarPersonas(Persona persona) {
        personas.add(persona);
    }
    
    //Ej 3.2
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public ArrayList<Persona> listarPersonas() {
        return personas;
    }
    
    //Ej 3.3
    @GET
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Persona verPersona(@PathParam("cadena") String cadena) {
        for (Persona persona : personas) {
            if (persona.getNombre().equals(cadena)) {
                return persona;
            }
        }
        return null;
    }
    
    //Ej 3.4
    @GET
    @Path("/buscar")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Persona> ver(@QueryParam("cadena") String cadena) {
        ArrayList<Persona> personasConCadena = new ArrayList<>();
        String cadenaLower = cadena.toLowerCase().trim();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(cadenaLower)) {
                personasConCadena.add(p);
            }
        }
        return personasConCadena;
    }

    //Ej 3.6 
    @POST
    @Path("/form")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Persona> insertarPorFormulario(@FormParam("id") int id,
    @FormParam("nombre") String nombre,
    @FormParam("casado") boolean casado,
    @FormParam("sexo") String sexo) {
        Persona p = new Persona();
        p.setId(id);
        p.setNombre(nombre);
        p.setCasado(casado);
        p.setSexo(sexo);
        personas.add(p);
        return personas;
    }
    
    //Ej 3.7
    @POST
    @Path("/add")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response insertarVariasPersonas() {
        for (Persona persona : personas) {
            personas.add(persona);
        }
        return Response.ok(personas).build();
    }

    //Ej 3.8
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response eliminarPorId(@PathParam("id") int id) {
        for (int i = personas.size(); i > 0; i--) {
            if (personas.get(i).getId() == id) {
                personas.remove(personas.get(i));
            }
        }
        return Response.ok(personas).build();
    }
    
    //Ej 3.9
    @GET
    @Path("/buscar2")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response verQueryParamPorDefecto(@DefaultValue("a") @QueryParam("cadena") String cadena){
        ArrayList<Persona> personasConCadena = new ArrayList<>();
        String cadenaLower = cadena.toLowerCase().trim();
        for (Persona persona : personas) {
            if (persona.getNombre().toLowerCase().contains(cadenaLower)) {
                personasConCadena.add(persona);
            }
        }
        return Response.ok(personasConCadena).build();
    }
}