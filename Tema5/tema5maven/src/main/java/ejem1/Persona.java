package ejem1;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Persona {
    private int id;
    private String nombre;
    private boolean casado;
    private String sexo;

    public Persona(){

    }
    
    public Persona(int id, String nombre, boolean casado, String sexo){
        this.id = id;
        this.nombre = nombre;
        this.casado = casado;
        this.sexo = sexo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String cadena) {
        this.nombre = cadena;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getCasado() {
        return casado;
    }

    public String getSexo() {
        return sexo;
    }
}
