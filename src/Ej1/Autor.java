package Ej1;

public class Autor {
    private String nombre;
    private int fecha;

    // Constructor
    public Autor(String nombre, int fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Autor() {
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getFecha() {
        return fecha;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    // toString (opcional)
    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}

