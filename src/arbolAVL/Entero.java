package arbolAVL;

import java.util.Objects;

public class Entero implements Comparable{
	private int dato;
	public Entero(int dato) {
		this.dato = dato;
	}
	public Entero() {
		this(0);
	}
	public int getDato() {
		return dato;
	}
	public void setDato(int dato) {
		this.dato = dato;
	}
	public boolean esIgual(Object q) {
		Entero obj =(Entero)q;
		return (dato==obj.getDato());
	}
	public boolean esMenor(Object q) {
		Entero obj =(Entero)q;
		return (dato<obj.getDato());
	}
	public boolean esMayor(Object q) {
		Entero obj =(Entero)q;
		return (dato>obj.getDato());
	}
	@Override
	public String toString() {
		return ""+dato;
	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Entero entero = (Entero) obj;
        return dato == entero.dato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dato);
    }
}
	
