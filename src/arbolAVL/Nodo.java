package arbolAVL;
public class Nodo {
    private Comparable valor;
	private Nodo izquierdo;
	private Nodo derecho;
	private int fe;//Factor de equilibrio
	public Nodo() {
		valor = null;
		izquierdo = null;
		derecho= null;
        this.fe =0;
	}
	public Nodo(Comparable valor) {
		this.valor = valor;
		izquierdo = null;
		derecho = null;
        this.fe=0;
		
	}
	
	public int getFe() {
		return fe;
	}
	public void setFe(int fe) {
		this.fe = fe;
	}
	public Comparable getValor() {
		return valor;
	}
	public void setValor(Comparable valor) {
		this.valor = valor;
	}
	public Nodo getIzquierdo() {
		return izquierdo;
	}
	public void setIzquierdo(Nodo izquierdo) {
		this.izquierdo = izquierdo;
	}
	public Nodo getDerecho() {
		return derecho;
	}
	public void setDerecho(Nodo derecho) {
		this.derecho = derecho;
	}
	
}
