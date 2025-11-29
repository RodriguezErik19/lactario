package datos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha {
	private Integer dia; //se cambia de int a Integer para poder asignar null
	private Integer mes; //se cambia de int a Integer para poder asignar null
	private Integer anio; //se cambia de int a Integer para poder asignar null
	public Fecha(String s) {
		//buscamos la primera ocurrencia
		int pos1 = s.indexOf('/');
		//buscamos la ultima ocurrencia
		int pos2 = s.lastIndexOf('/');
		//Procesamos el dia
		String sDia = s.substring(0,pos1);
		dia = Integer.parseInt(sDia);
		//Procesamos el mes
		String sMes = s.substring(pos1+1,pos2);
		mes = Integer.parseInt(sMes);
		//Procesamos el anio
		String sAnio=s.substring(pos2+1);
		anio = Integer.parseInt(sAnio);
	}
	public Fecha() {
		this.dia = null;//1;
		this.mes = null;//1;
		this.anio = null;//2000;
		
	}
	public Fecha(int dia, int mes, int anio) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public Fecha obtenerFechaSistema() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaActual = LocalDate.now();
        String fechaFormateada = fechaActual.format(formatter);
		return new Fecha(fechaFormateada);
		
    }
	/**
	 * Calcula la edad en meses entre la fecha actual (this) y la fecha de nacimiento recibida.
	 * Debes considerar años, meses y días para obtener el total de meses completos.
	 * Ejemplo: Si la diferencia es 1 año y 3 meses, debe devolver 15 meses.
	 * @param fechaNacimiento Fecha de nacimiento
	 * @return Edad en meses
	 */
	public int calcularEdadMeses(Fecha fechaNacimiento) {
		// TODO: Implementar el cálculo de edad en meses
		return 0;
	}
	/**
	 * Calcula la edad en días entre la fecha actual (this) y la fecha de nacimiento recibida.
	 * Debes considerar años, meses y días para obtener el total de días completos.
	 * Ejemplo: Si la diferencia es 1 año, 2 meses y 5 días, debe devolver el total de días.
	 * @param fechaNacimiento Fecha de nacimiento
	 * @return Edad en días
	 */
	public int calcularEdadDias(Fecha fechaNacimiento) {
		// TODO: Implementar el cálculo de edad en días
		return 0;
	}
		/**
		 * Calcula la edad en años entre la fecha actual (this) y la fecha de nacimiento recibida.
		 * Debes considerar si el cumpleaños ya pasó este año para ajustar el resultado.
		 * Ejemplo: Si nació en 2000 y estamos en 2025 pero aún no cumple años, debe devolver 24.
		 * @param fechaNacimiento Fecha de nacimiento
		 * @return Edad en años
		 */
		public int calcularEdadAnios(Fecha fechaNacimiento) {
			// TODO: Implementar el cálculo de edad en años
			return 0;
		}
	
	
	@Override
	public String toString() {
		//return "Fecha [dia=" + dia + ", mes=" + mes + ", anio=" + anio + "]";
		return dia+"/"+mes+"/"+anio;
	}
}
