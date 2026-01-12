package ejem1;
import java.io.Serializable;

public class Persona implements Serializable{
    public int id;
    public String cadena;
    public boolean casado;
    public String sexo;

    public void setId(int id) {
        this.id = id;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
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

    public String getCadena() {
        return cadena;
    }

    public boolean getCasado() {
        return casado;
    }

    public String getSexo() {
        return sexo;
    }
}
