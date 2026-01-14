package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
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
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void guardarPersonas(Persona persona) {
        personas.add(persona);
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Persona> listarPersonas() {
        return personas;
    }

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

    @GET
    @Path("/buscar")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ArrayList<Persona> ver(@QueryParam("cadena") String cadena) {
        ArrayList<Persona> personasConCadena = new ArrayList<>();
        String cadenaLower = cadena.toLowerCase();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(cadenaLower)) {
                personasConCadena.add(p);
            }
        }
        return personasConCadena;
    }

    // @GET
    // @Path("/form")
    // @Produces(MediaType.TEXT_HTML)
    // public String formularioHtml() {
    //     return "<html>"
    //             + "<body>"
    //             +"<form method=\"POST\" action=\"http://localhost:8080/tema5maven/rest/personas/form\">"
    //             + "<h1>Formulario</h1>"
    //             + "<input type=\"number\" name=\"id\" id=\"id\" placeholder=\"id\"><br><br>"
    //             + "<input type=\"text\" name=\"nombre\" id=\"nombre\" placeholder=\"Nombre\"><br><br> "
    //             + " <span>Estado civil: </span>"
    //             + " <input type=\"radio\" name=\"casado\" id=\"casado\" checked value=\"true\"><label for=\"genero1\">Casado</label> "
    //             + " <input type=\"radio\" name=\"casado\" id=\"soltero\" value=\"false\"><label for=\"genero2\">Soltero</label><br> "
    //             + "<span>Sexo: </span>"
    //             + "<input type=\"radio\" name=\"sexo\" id=\"genero1\" checked value=\"hombre\"><label for=\"genero1\">Hombre</label>"
    //             + "<input type=\"radio\" name=\"sexo\" id=\"genero2\" value=\"mujer\"><label for=\"genero2\">Mujer</label>"
    //             + "</form>"
    //             + "</body>"
    //             + "</html>";
    // }

    @POST
    @Path("/form")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Persona> getCarText(@FormParam("id") int id,
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

    
}