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
    public Depart(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    public Depart(){
        this(0, "");
    }
}
