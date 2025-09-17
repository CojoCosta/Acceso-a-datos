package FicherosBinarios;

import java.io.Serializable;

class Cliente implements Serializable{
    private String nombre;
    private int numCompras;
    private float credito;

    public Cliente(String nombre, int numCompras, float credito) {
        this.nombre = nombre;
        this.numCompras = numCompras;
        this.credito = credito;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumCompras() {
        return numCompras;
    }

    public float getCredito() {
        return credito;
    }
}
