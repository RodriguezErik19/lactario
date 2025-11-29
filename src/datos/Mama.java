
package datos;

import listaDoble.ListaDoblementeEnlazada;

public class Mama {
   public String getIniciales() {
        if (this.nombre == null || this.nombre.isEmpty()) {
            return "";
        }

        String iniciales = ""; 
        boolean buscarInicial = true; 

        for (int i = 0; i < this.nombre.length(); i++) {
            char actual = this.nombre.charAt(i);

            if (actual == ' ') {
                buscarInicial = true;
            } else if (buscarInicial) { //crea un nuevo string en cvada concatenacion 
                iniciales = iniciales + Character.toUpperCase(actual); 
                buscarInicial = false; 
            }
        }

        return iniciales;
    }

    private String id;
    private String nombre;
    private int edad;
    private Fecha fechaNacimiento;
    private ListaDoblementeEnlazada<Hijo> hijos;

    public Mama(String id, String nombre, int edad, Fecha fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Mama(String id, String nombre, int edad, Fecha fechaNacimiento, ListaDoblementeEnlazada<Hijo> hijos) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.hijos = hijos;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public Fecha getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Fecha fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public ListaDoblementeEnlazada<Hijo> getHijos() { return hijos; }
    public void setHijos(ListaDoblementeEnlazada<Hijo> hijos) { this.hijos = hijos; }

    public boolean esMenor(Object q) {
        if (q instanceof Mama) {
            Mama otraMama = (Mama) q;
            return this.id.compareTo(otraMama.getId()) < 0;
        }
        return false;
    }

    public boolean esMayor(Object q) {
        if (q instanceof Mama) {
            Mama otraMama = (Mama) q;
            return this.id.compareTo(otraMama.getId()) > 0;
        }
        return false;
    }

    public boolean esIgual(Object q) {
        if (q instanceof Mama) {
            Mama otraMama = (Mama) q;
            return this.id.equals(otraMama.getId());
        }
        return false;
    }

    public String getIdNombreCompleto() { return id + "-" + nombre; }

    @Override
    public String toString() {
        try {
            int idNum = Integer.parseInt(id);
            return String.format("%03d", idNum);
        } catch (NumberFormatException e) {
            return id;
        }
    }
}


