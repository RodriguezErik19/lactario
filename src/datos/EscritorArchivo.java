package datos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import listaDoble.ListaDoblementeEnlazada;
import listaDoble.PosicionIlegalException;
import arbolAVL.ArbolAVL;

public class EscritorArchivo {
    private static final String DATA_DIR = "data";

    /**
     * Guarda las mamás y los hijos en archivos de texto
     * @param mamas Lista de mamás a guardar
     * @param hijos Lista de hijos a guardar
     */
    public void guardarArchivos(ListaDoblementeEnlazada<Mama> mamas, 
                                ListaDoblementeEnlazada<Hijo> hijos) {
        guardarMamas(mamas);
        guardarHijos(hijos);
    }

    /**
     * Guarda las mamás en mamas.txt
     */
    private void guardarMamas(ListaDoblementeEnlazada<Mama> mamas) {
        new java.io.File(DATA_DIR).mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIR + "/mamas.txt"))) {
            for (int i = 0; i < mamas.getTamanio(); i++) {
                try {
                    Mama mama = mamas.getValor(i);
                    writer.printf("\"%s\",\"%s\",\"%d\",\"%s\",null%n",
                        mama.getId(),
                        mama.getNombre(),
                        mama.getEdad(),
                        mama.getFechaNacimiento().toString()
                    );
                } catch (PosicionIlegalException e) {
                    System.err.println("Error al guardar mamá en posición " + i);
                    e.printStackTrace();
                }
            }
            System.out.println("Datos de mamás guardados exitosamente en data/mamas.txt");
        } catch (IOException e) {
            System.err.println("Error al escribir archivo data/mamas.txt");
            e.printStackTrace();
        }
    }

    /**
     * Guarda los hijos en hijos.txt
     */
    private void guardarHijos(ListaDoblementeEnlazada<Hijo> hijos) {
        new java.io.File(DATA_DIR).mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIR + "/hijos.txt"))) {
            for (int i = 0; i < hijos.getTamanio(); i++) {
                try {
                    Hijo hijo = hijos.getValor(i);
                    writer.printf("\"%s\",\"%s\",\"%02d\",\"meses\",\"%02d\",\"dias\",\"%s\",\"%s\"%n",
                        hijo.getId(),
                        hijo.getNombre(),
                        hijo.getEdadMeses(),
                        hijo.getEdadDias(),
                        hijo.getFechaNacimiento().toString(),
                        hijo.getIdMama()
                    );
                } catch (PosicionIlegalException e) {
                    System.err.println("Error al guardar hijo en posición " + i);
                    e.printStackTrace();
                }
            }
            System.out.println("Datos de hijos guardados exitosamente en data/hijos.txt");
        } catch (IOException e) {
            System.err.println("Error al escribir archivo data/hijos.txt");
            e.printStackTrace();
        }
    }
}
