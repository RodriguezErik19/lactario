package arbolAVL;
public class Estudiante implements Comparable{

	private int codigo;
	private String nombre;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Estudiante(int codigo, String nombre) {
		
		this.codigo = codigo;
		this.nombre = nombre;
	}
	public boolean esMenor(Object q) {
		Estudiante obj = (Estudiante)q;
		return (codigo<obj.getCodigo());
	}
	public boolean esMayor(Object q) {
		Estudiante obj = (Estudiante)q;
		return (codigo>obj.getCodigo());
	}
	public boolean esIgual(Object q) {
		Estudiante obj = (Estudiante)q;
		return (codigo==obj.getCodigo());
	}
	@Override
	public String toString() {
		return  codigo + " " + nombre ;
	}
	
	
		

}

