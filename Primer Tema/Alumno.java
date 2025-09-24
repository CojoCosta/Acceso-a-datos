class Alumno {
    private String nombre;
    private int codigo;
    private float altura;

    public Alumno(){
        this("Diego",7,1.74F);
    }
    public Alumno(String nombre, int codigo, float altura) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.altura = altura;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
}
