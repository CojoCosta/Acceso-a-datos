import java.io.Serializable;

public class Perro implements Serializable{
    private String nombre;
    private int edad;
    private float peso;

    public int getEdad() {
        return edad;
    } public String getNombre() {
        return nombre;
    }public float getPeso() {
        return peso;
    }public void setEdad(int edad) {
        this.edad = edad;
    }public void setNombre(String nombre) {
        this.nombre = nombre;
    }public void setPeso(float peso) {
        this.peso = peso;
    }
    public Perro(String nombre, int edad, float peso){
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso; 
    }
    @Override
    public String toString() {
        return String.format("nombre: %s; edad: %d; peso: %.2f\n",nombre, edad, peso);
    }
}
