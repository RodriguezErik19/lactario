package uI;

import java.util.Scanner;


import datos.Hijo;

import datos.Fecha;
import datos.Mama;
import listaDoble.ListaDoblementeEnlazada;
import listaDoble.PosicionIlegalException;
import negocios.Lactario;
import arbolAVL.BTreePrinter;


public class MenuOpciones {
	static Scanner entrada = new  Scanner(System.in);
	private Lactario lact = new Lactario();
	static Fecha fechaActual = new Fecha("00/00/0000").obtenerFechaSistema();
	
	/**
	 * Getter para acceder al Lactario desde otras clases
	 */
	public Lactario getLactario() {
		return lact;
	}
	
	
	/**
	 * Agregar un elemento al árbol AVL, validando que no exista
	 * Agregar un elemento en un TreeSet
	 */
	public void agregar_mama() {
		try {
			String id;
			String nombre;
			int edad;
			Fecha fecha;
			ListaDoblementeEnlazada<Hijo> hijosMama = new ListaDoblementeEnlazada<Hijo>();
			
			System.out.println(" *** INGRESAR MADRE ***");
			System.out.println("\t\tFecha Actual:"+fechaActual);
			// Mostrar datos completos de la última mamá registrada (si existe)
			Mama ultima = lact.getUltimaMama();
			if (ultima != null) {
				System.out.println("Última mamá registrada:");
			System.out.println("  ID: " + ultima.getId());
			System.out.println("  Nombre: " + ultima.getNombre());
			System.out.println("  Edad: " + ultima.getEdad());
			System.out.println("  Fecha Nac: " + ultima.getFechaNacimiento());
			int numHijos;
			if (ultima.getHijos() != null) {
				numHijos = ultima.getHijos().getTamanio();
			} else {
				numHijos = 0;
			}
			System.out.println("  Hijos registrados: " + numHijos);
			}
			do {
				System.out.println("DEBE INGRESAR UN ID QUE NO EXISTA");
				id = leerLineaNoVacia("Introduce ID: ");
			} while (lact.buscarMama(id) != null);

			System.out.print("Introduce Nombre MADRE (puede contener espacios): ");
			nombre = leerLineaNoVacia("");
			
			// TODO: Validar que la edad capturada coincida con la fecha de nacimiento de la madre
			edad = leerEntero("Introduce Edad MADRE: ");
			fecha = leerFechaValida("Introduce Fecha Nacimiento MADRE (DD/MM/AAAA): ");
	
			final String CENTINELA="99";
			String idHijo = leerLineaNoVacia(" *** CAPTURA DE HIJO/HIJA DE LA MADRE ***\nIntroduce ID Hijo/Hija (99 para Salir): ");
			while (!idHijo.equals(CENTINELA)) {
				while (lact.buscaHijo(idHijo) != null) {
					idHijo = leerLineaNoVacia("DEBE INGRESAR UN ID QUE NO EXISTA\nIntroduce ID Hijo/Hija: ");
				}
				String nombreHijo = leerLineaNoVacia("Introduce Nombre Hijo/Hija (puede contener espacios): ");
				Fecha fechaHijo;
				int edadMeses;
				int edadDias;
				// TODO: Validar que la edad del hijo sea menor a 24 meses
				fechaHijo = leerFechaValida("Introduce Fecha Nacimiento Hijo/Hija (DD/MM/AAAA): ");
				edadMeses = fechaActual.calcularEdadMeses(fechaHijo);
				edadDias = fechaActual.calcularEdadDias(fechaHijo) - edadMeses * 30;
				System.out.println("Edad en Meses:" + edadMeses);
				System.out.println("Edad en Dias:" + edadDias);
				lact.agregar_hijos(idHijo, nombreHijo, edadMeses, edadDias, fechaHijo, id, nombre);
				hijosMama.agregar(new Hijo(idHijo, nombreHijo, edadMeses, edadDias, fechaHijo, id));
				idHijo = leerLineaNoVacia("Introduce ID Hijo/Hija (99 para Salir): ");
			}
			//Agrega un objeto mama al árbol AVL y al hashTable
			lact.agregar_mama(id, nombre, edad, fecha,lact.getHijos(id));
			
			System.out.println("Captura Hijos/Hijas Finalizada");
			ListaDoblementeEnlazada<Hijo> hijos = lact.obtenerListaLactantes();
			System.out.println("Historial de Hijos/Hijas: "+hijos);
			
			System.out.println("Insertado con éxito");
			
		}
		catch (Exception e) {
			e.printStackTrace();
				
		}
		
	}
	/**
	 * Elimina un elemento del árbol AVLL validando que el elemento exista
	 * Elimina un elemento de un TreeSet
	 */
	public void eliminar_mama() {
		try {
			String id;
			Mama objMama = null;

			System.out.println(" *** ELIMINAR MADRE ***");
			System.out.println("\t\tFecha Actual:"+fechaActual);

			do {
				System.out.println("DEBE INGRESAR UN ID QUE EXISTA");
				System.out.print("Introduce ID:");
				id = entrada.next();
				objMama= lact.buscarMama(id);

			}while (objMama==null);
			
			System.out.println("Nombre:"	+objMama.getNombre());
			System.out.println("Edad:"+objMama.getEdad());
			System.out.println("Fecha (MM/DD/AAAA):"+objMama.getFechaNacimiento());

			if (objMama.getHijos().getTamanio()==0) {
				System.out.println("No tiene Hijos registrados");
			}
			else{
				System.out.println("Número de Hijos:"+objMama.getHijos().getTamanio());
				for(int i=0;i<objMama.getHijos().getTamanio();i++) {
					System.out.println(objMama.getHijos().getValor(i));
			}
		}

			String respuesta = leerLineaNoVacia("¿Está seguro de eliminar este registro? (S/N) ");
		if (respuesta.equalsIgnoreCase("S")) {
			lact.eliminar_mama(id);
			lact.eliminar_hijo_mama(id);
			System.out.println("Eliminado con éxito");
		}
		else
			System.out.println("Operación Cancelada");
		
		   
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	/*
	 * Edita un elemento del árbol AVL
	 */
	public void editar_mama() {
		try {
			String id;
			Mama objMama = null;

			System.out.println(" *** EDITAR MAMA ***");
			System.out.println("\t\tFecha Actual:"+fechaActual);

			do {
				System.out.println("DEBE INGRESAR UN ID QUE  EXISTA");
				id = leerLineaNoVacia("Introduce ID: ");
				//busca en Hashtable
				objMama= lact.buscarMama(id);

			}while (objMama==null);
			String nombre = objMama.getNombre();
			Fecha fechaNacimiento = objMama.getFechaNacimiento();
			int edad = objMama.getEdad();
			
			System.out.println("Nombre:"	+nombre);
			System.out.println("Edad:"+edad);
			System.out.println("Fecha (MM/DD/AAAA):"+fechaNacimiento);

			if (objMama.getHijos().getTamanio()==0) {
				System.out.println("No tiene Hijos registrados");
			}
			
			else{
				System.out.println("Número de Hijos:"+objMama.getHijos().getTamanio());
				for(int i=0;i<objMama.getHijos().getTamanio();i++) {
					System.out.println(objMama.getHijos().getValor(i));
				}
			}
			String respuesta = leerLineaNoVacia("¿Está es el registro que quiere editar? (S/N) ");
			if (respuesta.equalsIgnoreCase("S")) {
					
				System.out.print("Introduce Nombre MADRE (puede contener espacios): ");
				entrada.nextLine(); // limpiar salto pendiente
				String nombre2;
				do {
					nombre2 = entrada.nextLine().trim();
					if (nombre2.isEmpty()) {
						System.out.print("ERROR: El nombre no puede quedar en blanco. Ingrese nuevamente: ");
					}
				} while (nombre2.isEmpty());
				
				// Validar edad con fecha de nacimiento
				int edad2;
				Fecha fechaNacimiento2;
				boolean edadValida = false;
				do {
					edad2 = leerEntero("Introduce Edad MADRE: ");
					fechaNacimiento2 = leerFechaValida("Introduce Fecha Nacimiento MADRE (DD/MM/AAAA): ");
					
					int edadCalculada = fechaActual.calcularEdadAnios(fechaNacimiento2);
					if (edad2 == edadCalculada) {
						edadValida = true;
					} else {
						System.out.println("ERROR: La edad capturada (" + edad2 + ") no coincide con la fecha de nacimiento.");
						System.out.println("       Según la fecha ingresada, la edad debería ser: " + edadCalculada + " años.");
						System.out.println("       Por favor, verifique los datos e intente nuevamente.");
					}
				} while (!edadValida);
				String respuesta2 = leerLineaNoVacia("¿Procede edición? (S/N) ");
				if (respuesta2.equalsIgnoreCase("S")) {
					Mama mama =lact.buscar_mama(id,nombre,edad,fechaNacimiento);
					
					if(mama !=null){
						//modifica el objeto mama del arbol AVL
						mama.setNombre(nombre2);
					    mama.setEdad(edad2);
					    mama.setFechaNacimiento(fechaNacimiento2);

					}
					//modifica el objeto mama del hashTable
					objMama.setNombre(nombre2);
					objMama.setEdad(edad2);
					objMama.setFechaNacimiento(fechaNacimiento2);
					System.out.println("Editado con éxito");
				}
		
			}
			else
				System.out.println("Operación Cancelada");
		
		   
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	/**
	 * Busca un elemento en el árbolAVL  
	 */
	
	public void buscar_mama_id() {
		try {
			String id;
			Mama objMama = null;

			System.out.println(" *** BUSCAR MAMA ***");
			System.out.println("\t\tFecha Actual:"+fechaActual);

			do {
				System.out.println("DEBE INGRESAR UN ID QUE  EXISTA");
				id = leerLineaNoVacia("Introduce ID: ");
				//busca en Hashtable
				objMama= lact.buscarMama(id);

			}while (objMama==null);
			String nombre = objMama.getNombre();
			Fecha fechaNacimiento = objMama.getFechaNacimiento();
			int edad = objMama.getEdad();
			
			System.out.println("Nombre:"	+nombre);
			System.out.println("Edad:"+edad);
			System.out.println("Fecha (MM/DD/AAAA):"+fechaNacimiento);

			if (objMama.getHijos().getTamanio()==0) {
				System.out.println("No tiene Hijos registrados");
			}
			
			else{
				System.out.println("Número de Hijos:"+objMama.getHijos().getTamanio());
				for(int i=0;i<objMama.getHijos().getTamanio();i++) {
					System.out.println(objMama.getHijos().getValor(i));
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();

			
		}
		
	}

	public void buscarMamaPorNombreParcial() {
		try {
			String nombre;
			nombre = leerLineaNoVacia("Introduce parte del Nombre (puede contener espacios): ");
			ListaDoblementeEnlazada<Mama> mamas = lact.buscarMamaPorNombreParcial(nombre);
			if (mamas.getTamanio()>0) {
				System.out.println("Mamás encontradas con el nombre parcial: " + nombre);
				System.out.println("=".repeat(60));
				for(int i=0;i<mamas.getTamanio();i++) {
					Mama mama = mamas.getValor(i);
					System.out.println("ID: " + mama.getId());
					System.out.println("Nombre: " + mama.getNombre());
					System.out.println("Edad: " + mama.getEdad() + " años");
					System.out.println("Fecha Nacimiento: " + mama.getFechaNacimiento());
					int numHijos;
					if (mama.getHijos() != null) {
						numHijos = mama.getHijos().getTamanio();
					} else {
						numHijos = 0;
					}
					System.out.println("Hijos registrados: " + numHijos);
					System.out.println("-".repeat(60));
				}
			}
			else
				System.out.println("¡NO Existen datos con esas características!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarTodasMamasConOrdenamiento() {
		try {
			System.out.println("\n *** MOSTRAR TODAS LAS MAMÁS ***\n");
			System.out.println("Seleccione criterio de ordenamiento:");
			System.out.println("1. Por Nombre (alfabético)");
			System.out.println("2. Por ID (numérico)");
			System.out.println("3. Por Número de Hijos (descendente)");
			System.out.println("4. Por Edad (descendente)");
			System.out.print("Opción: ");
			int opcion = leerEntero("Opción: ");
			
			ListaDoblementeEnlazada<Mama> mamas = lact.obtenerListaMamas();
			if (mamas.getTamanio() == 0) {
				System.out.println("No hay mamás registradas.");
				return;
			}
			
			// Ordenar según criterio
			ordenarMamas(mamas, opcion);
			
			// Mostrar resultados
			System.out.println("\n" + "=".repeat(70));
			String criterio = "";
			if (opcion == 1) {
				criterio = "NOMBRE";
			} else if (opcion == 2) {
				criterio = "ID";
			} else if (opcion == 3) {
				criterio = "NÚMERO DE HIJOS";
			} else if (opcion == 4) {
				criterio = "EDAD";
			}
			System.out.println("MAMÁS ORDENADAS POR: " + criterio);
			System.out.println("=".repeat(70));
			
			for (int i = 0; i < mamas.getTamanio(); i++) {
				Mama m = mamas.getValor(i);
				int numHijos;
				if (m.getHijos() != null) {
					numHijos = m.getHijos().getTamanio();
				} else {
					numHijos = 0;
				}
				System.out.printf("ID: %-5s | Nombre: %-25s | Edad: %3d | Hijos: %2d%n",
						m.getId(), m.getNombre(), m.getEdad(), numHijos);
			}
			System.out.println("=".repeat(70));
			System.out.println("Total de mamás: " + mamas.getTamanio());
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void ordenarMamas(ListaDoblementeEnlazada<Mama> mamas, int criterio) throws PosicionIlegalException {
		// Algoritmo de ordenamiento burbuja mejorado
		int n = mamas.getTamanio();
		for (int i = 0; i < n - 1; i++) {
			boolean intercambio = false;
			for (int j = 0; j < n - i - 1; j++) {
				Mama m1 = mamas.getValor(j);
				Mama m2 = mamas.getValor(j + 1);
				boolean debeIntercambiar = false;
				
				if (criterio == 1) {
					// Por nombre (alfabético)
					debeIntercambiar = m1.getNombre().compareToIgnoreCase(m2.getNombre()) > 0;
				} else if (criterio == 2) {
					// Por ID (numérico)
					try {
						int id1 = Integer.parseInt(m1.getId());
						int id2 = Integer.parseInt(m2.getId());
						debeIntercambiar = id1 > id2;
					} catch (NumberFormatException e) {
						debeIntercambiar = m1.getId().compareTo(m2.getId()) > 0;
					}
				} else if (criterio == 3) {
					// Por número de hijos (descendente)
					int hijos1;
					if (m1.getHijos() != null) {
						hijos1 = m1.getHijos().getTamanio();
					} else {
						hijos1 = 0;
					}
					int hijos2;
					if (m2.getHijos() != null) {
						hijos2 = m2.getHijos().getTamanio();
					} else {
						hijos2 = 0;
					}
					debeIntercambiar = hijos1 < hijos2;
				} else if (criterio == 4) {
					// Por edad (descendente)
					debeIntercambiar = m1.getEdad() < m2.getEdad();
				}
				
				if (debeIntercambiar) {
					// Intercambiar elementos usando insertar
					mamas.remover(j);
					mamas.insertar(m1, j + 1);
					intercambio = true;
				}
			}
			if (!intercambio) {
				break;
			}
		}
	}
	
	public void mostrar_arbol_mamas() throws Exception{
		System.out.println("\n MOSTRAR ARBOL DE MAMAS\n");
		BTreePrinter.printNode(lact.getAbo().getRaiz());

	}
	public void mostrar_lista_mamas() throws Exception{
		System.out.println("\n MOSTRAR LISTA DE MAMAS\n");
		ListaDoblementeEnlazada<Mama> mamas = lact.obtenerListaMamas();
		for (int i = 0; i < mamas.getTamanio(); i++) {
			try {
				Mama m = mamas.getValor(i);
				System.out.println(m.getId() + " - " + m.getNombre());
			} catch (PosicionIlegalException e) {
				System.out.println("Error: PosicionIlegalException occurred.");
				e.printStackTrace();
			}
		}
	}
	
	
	public void mostrar_lista_lactantes() throws Exception{
		System.out.println("\n MOSTRAR LISTA DE LACTANTES\n");
		ListaDoblementeEnlazada<Hijo> lista = lact.obtenerListaLactantes();
		for (int i = 0; i < lista.getTamanio(); i++) {
			try {
				System.out.println(lista.getValor(i));
			} catch (PosicionIlegalException e) {
				System.out.println("Error: PosicionIlegalException occurred.");
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * Lee una fecha desde consola validando el formato DD/MM/AAAA y repite hasta que sea válida.
	 */
	private Fecha leerFechaValida(String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				String entradaFecha = entrada.next();
				return new Fecha(entradaFecha);
			} catch (Exception ex) {
				System.out.println("Fecha inválida. Formato esperado DD/MM/AAAA. Intente de nuevo.");
			}
		}
	}
	
	public void ver_ultimo_id_mama() {
		System.out.println("\n ÚLTIMO ID DE MAMÁ CAPTURADO\n");
		String ultimoId = lact.getUltimoIdCapturado();
		if (ultimoId == null) {
			System.out.println("Aún no se ha capturado ningún ID de mamá.");
		} else {
			System.out.println("Último ID: " + ultimoId);
		}
	}

	public void agregar_hijo_a_mama() {
		try {
			System.out.println("\n *** AGREGAR HIJO A MAMÁ EXISTENTE ***\n");
			String idMama = leerLineaNoVacia("Introduce ID de la Mamá: ");
			
			Mama mama = lact.buscarMama(idMama);
			if (mama == null) {
				System.out.println("ERROR: No existe una mamá con el ID: " + idMama);
				return;
			}
			
			System.out.println("Mamá encontrada: " + mama.getNombre());
			System.out.println("Hijos actuales: " + (mama.getHijos() != null ? mama.getHijos().getTamanio() : 0));
			
			System.out.println("\n *** CAPTURA DE HIJO/HIJA ***");
			String idHijo;
			while (true) {
				idHijo = leerLineaNoVacia("Introduce ID Hijo/Hija: ");
				if (lact.buscaHijo(idHijo) != null) {
					System.out.println("ERROR: Ya existe un hijo con ese ID. Intente otro.");
					continue;
				}
				break;
			}
			
			String nombreHijo = leerLineaNoVacia("Introduce Nombre Hijo/Hija: ");
			
			int edadMeses = leerEntero("Introduce Edad en MESES: ");
			int edadDias = leerEntero("Introduce Edad en DÍAS: ");
			
			// Validar fecha de nacimiento del hijo (<24 meses) y recalcular edadMeses/edadDias
			Fecha fechaHijo;
			int edadMesesCalc;
			int edadDiasCalc;
			while (true) {
				fechaHijo = leerFechaValida("Introduce Fecha Nacimiento (DD/MM/AAAA): ");
				edadMesesCalc = fechaActual.calcularEdadMeses(fechaHijo);
				if (edadMesesCalc >= 24) {
					System.out.println("ERROR: La edad del hijo calculada (" + edadMesesCalc + " meses) es mayor o igual a 24 meses. Ingrese una fecha válida (<24 meses).");
					continue;
				}
				edadDiasCalc = fechaActual.calcularEdadDias(fechaHijo) - edadMesesCalc * 30;
				break;
			}
			// Sobrescribir edades proporcionadas manualmente para garantizar consistencia
			edadMeses = edadMesesCalc;
			edadDias = edadDiasCalc;
			// No consumir línea adicional: leerFechaValida usa next(), no nextLine
			lact.agregar_hijos(idHijo, nombreHijo, edadMeses, edadDias, fechaHijo, idMama, mama.getNombre());
			
			// Actualizar la lista de hijos de la mamá
			if (mama.getHijos() == null) {
				mama.setHijos(new ListaDoblementeEnlazada<Hijo>());
			}
			mama.getHijos().agregar(new Hijo(idHijo, nombreHijo, edadMeses, edadDias, fechaHijo, idMama));
			
			System.out.println("✓ Hijo agregado exitosamente a " + mama.getNombre());
			lact.guardarDatos();
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Helpers de entrada robusta para pruebas automatizadas
	private String leerLineaNoVacia(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String linea = entrada.nextLine().trim();
			if (linea.isEmpty()) {
				System.out.println("ERROR: El valor no puede quedar en blanco.");
				continue;
			}
			return linea;
		}
	}

	private int leerEntero(String mensaje) {
		while (true) {
			System.out.print(mensaje);
			String linea = entrada.nextLine().trim();
			try {
				return Integer.parseInt(linea);
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Debe ingresar un número entero válido.");
			}
		}
	}

	public void eliminar_hijo() {
		try {
			System.out.println("\n *** ELIMINAR HIJO ***\n");
			String idHijo = leerLineaNoVacia("Introduce ID del Hijo a eliminar: ");
			
			Hijo hijo = lact.buscaHijo(idHijo);
			if (hijo == null) {
				System.out.println("ERROR: No existe un hijo con el ID: " + idHijo);
				return;
			}
			
			System.out.println("Hijo encontrado: " + hijo.getNombre());
			System.out.println("Mamá: " + hijo.getIdMama());
			String confirma = leerLineaNoVacia("¿Confirma la eliminación? (S/N): ").toUpperCase();
			
				if (confirma.equals("S")) {
					boolean eliminado = lact.eliminar_hijo(idHijo);
				if (eliminado) {
					// Actualizar lista de hijos de la mamá
					Mama mama = lact.buscarMama(hijo.getIdMama());
					if (mama != null && mama.getHijos() != null) {
						for (int i = 0; i < mama.getHijos().getTamanio(); i++) {
							if (mama.getHijos().getValor(i).getId().equals(idHijo)) {
								mama.getHijos().remover(i);
								break;
							}
						}
					}
						System.out.println("✓ Hijo eliminado exitosamente");
						lact.guardarDatos();
				} else {
					System.out.println("ERROR: No se pudo eliminar el hijo");
				}
			} else {
				System.out.println("Operación cancelada");
			}
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
	
	
	

	

