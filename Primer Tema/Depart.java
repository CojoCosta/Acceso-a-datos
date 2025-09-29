public class Depart {
    public int codigo;
    public String nombre;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public Depart(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }
    public Depart(){
        this("", 0);
    }

    @Override
    public String toString() {
        return nombre + " / " + codigo;
    }
}
