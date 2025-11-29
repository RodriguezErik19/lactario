package datos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import listaDoble.ListaDoblementeEnlazada;
import arbolAVL.ArbolAVL;
import java.util.Map;
import pilas.Pila;


public class LectorArchivo {

    public void leerArchivo( ArbolAVL abo, ListaDoblementeEnlazada<Hijo> hijos, Map<String,Mama> ht, Pila<String> pilaIds) 
                             {
        
        
        String rutaArchivoMamas = "data/mamas.txt";
        String rutaArchivoHijos = "data/hijos.txt";
        // Cada mamá tendrá su propia lista de hijos; se creará al leer mamás

        try (Scanner scanner = new Scanner(new File(rutaArchivoMamas))) {
            // Leer línea a línea para evitar conflicto entre delimitadores y nextLine
            while (scanner.hasNextLine()) {
                try {
                    String linea = scanner.nextLine().trim();
                    if (linea.isEmpty()) continue;
                    String[] campos = linea.split(",");

                    String id = campos[0].replaceAll("\"", "");
                    String nombre = campos[1].replaceAll("\"", "");
                    int edad = Integer.parseInt(campos[2].replaceAll("\"", ""));
                    String fechaNac = campos[3].replaceAll("\"", "");
                   

                    Mama objMama = new Mama(id, nombre, edad, new Fecha(fechaNac), new ListaDoblementeEnlazada<Hijo>());
                    abo.insertar(objMama);
                    ht.put(id, objMama);
                    pilaIds.apilar(id); // registrar ID leído
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }

                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       
        try (Scanner scannerHijos = new Scanner(new File(rutaArchivoHijos))) {
            while (scannerHijos.hasNextLine()) {
                String linea = scannerHijos.nextLine().trim();
                if (linea.isEmpty()) continue;
                String[] campos = linea.split(",");

                String codigo = campos[0].replaceAll("\"", "");
                String nombre = campos[1].replaceAll("\"", "");
                int meses  =Integer.parseInt(campos[2].replaceAll("\"", ""));
                String tipoMes =campos[3].replaceAll("\"", "");
                int dias =Integer.parseInt(campos[4].replaceAll("\"", ""));
                String tipoDias =campos[5].replaceAll("\"", "");
          
                
                String fechaNac = campos[6].replaceAll("\"", "");
                String idMama = campos[7].replaceAll("\"", "");
               
               
                Hijo objHijo = new Hijo(codigo, nombre, meses,dias, new Fecha(fechaNac), idMama);
                hijos.agregar(objHijo); // lista global
                Mama madre = ht.get(idMama);
                if (madre != null) {
                    ListaDoblementeEnlazada<Hijo> lista = madre.getHijos();
                    if (lista == null) {
                        lista = new ListaDoblementeEnlazada<Hijo>();
                        madre.setHijos(lista);
                    }
                    lista.agregar(objHijo);
                }
               
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            

    }
}