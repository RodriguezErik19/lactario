package datos;
public class Hijo {
	private String id;
	private String nombre;
	private int edadMeses;
	private int edadDias;
	private Fecha fechaNacimiento;

	private String idMama;
   
	
	public Hijo() {
		super();
	};
	public Hijo(String id, String nombre, int edadDias, int edadMeses,Fecha fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edadMeses = edadMeses;
		this.edadDias = edadDias;
		
		this.fechaNacimiento = fechaNacimiento;

		
		
	}
	
	
	public Hijo(String id, String nombre, int edadDias, int edadMeses,Fecha fechaNacimiento, String idMama) {
		super();
		this.id = id;
		this.nombre = nombre;
		
		this.edadMeses = edadMeses;
		this.edadDias = edadDias;
		
		this.fechaNacimiento = fechaNacimiento;
		this.idMama = idMama;
        
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public int getEdadMeses() {
		return edadMeses;
	}
	public void setEdadMeses(int edadMeses) {
		this.edadMeses = edadMeses;
	}
	public int getEdadDias() {
		return edadDias;
	}
	public void setEdadDias(int edadDias) {
		this.edadDias = edadDias;
	}
	
	public Fecha getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Fecha fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

    public String getIdMama() {
		return idMama;
	}
	public void setIdMama(String idMama) {
		this.idMama = idMama;
	}
	
	@Override
	public String toString() {
		return id + ", " + nombre + ", " + edadMeses + " meses, " + edadDias
				+ " días, nacio en: " + fechaNacimiento + " ,clave de mama:  " + idMama;
	}
	

}
	