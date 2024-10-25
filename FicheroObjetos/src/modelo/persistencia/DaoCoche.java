package modelo.persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import modelo.entidad.Coche;

public class DaoCoche {
	private static final String NOMBRE_FICHERO = "coches.dat";
	private static final String FICHERO_CONTADOR_ID = "id_counter.txt"; // Archivo para almacenar el contador
    private static int contadorCoches; // Contador de ID único
	ArrayList<Coche> listaCoches;
	
    public DaoCoche() {
        // Cargar el último valor del contador desde el archivo, o inicializarlo en 9900
        contadorCoches = cargarContadorID();
    }

	/**
	 * Metodo que dado un ID pasado por parametro busca su coincidencia en el
	 * fichero y devuelve dicho objeto con sus datos.
	 * 
	 * @param id a buscar en el fichero.
	 * @return Coche en caso de que se encuentre en el fichero, en caso contrario
	 *         devuelve null.
	 * @throws <b>Exception</b>, en caso de que haya algún problema con el fichero.
	 */
    public Coche getById(long id) throws Exception {
        try (FileInputStream fichero = new FileInputStream(NOMBRE_FICHERO);
             ObjectInputStream lector = new ObjectInputStream(fichero)) {
             
            while (true) {
                try {
                    Coche c = (Coche) lector.readObject();
                    if (id == c.getID()) {
                        return c;
                    }
                } catch (EOFException e1) {
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error al leer los coches del archivo");
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        }
        return null;
    }

	/**
	 * Metodo que registra un coche pasado por parametro.Comprueba la existencia
	 * del fichero en caso de que no exista devuelve una excepcion
	 * @param c coche que pasamos por parametro
	 * @throws Exception
	 */
    public void registrar(Coche c) throws Exception {
        c.setID(++contadorCoches); // Asignar un ID único
        guardarContadorID(); // Guardar el último valor del contador

        ArrayList<Coche> listaCoches = getListaCoches();
        listaCoches.add(c);

        try (FileOutputStream fichero = new FileOutputStream(NOMBRE_FICHERO);
             ObjectOutputStream escritor = new ObjectOutputStream(fichero)) {
            
            for (Coche coche : listaCoches) {
                escritor.writeObject(coche);
            }
            System.out.println("El objeto ha sido guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Metodo que lee el fichero y lo almacena en un arrayList
	 * @return devuelve un arrayList de coches
	 * @throws Exception
	 */
    public ArrayList<Coche> getListaCoches() throws Exception {
        listaCoches = new ArrayList<>();
        
        File file = new File(NOMBRE_FICHERO);
        //Comprueba si el archivo coches.dat existe, sino devuelve un array vacio.
        if (!file.exists() || file.length() == 0) {
            return listaCoches;
        }
        
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Coche coche = (Coche) ois.readObject();
                    listaCoches.add(coche);
                } catch (EOFException e1) {
                    break;
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error al leer el archivo de coches.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("No se ha podido abrir el archivo de coches.");
            e.printStackTrace();
        }
        return listaCoches;
    }

	/**
	 * Metodo que borra un coche mediante un ID pasado por parametro. Comprueba 
	 * si el arrayLIst de coches esta vacio o es nulo, en caso afirmativo devulve una
	 * excepcion.
	 * Si el ID coincide lo borra y sobreescribe el array.
	 * @param coche
	 * 
	 * @throws Exception
	 */
    public void borrarCocheById(long id) throws Exception {
        ArrayList<Coche> listaCoches = getListaCoches();
        
        ArrayList<Coche> listaCochesActualizado = new ArrayList<>();
        boolean encontrado = false;

        for (Coche coche : listaCoches) {
            if (coche.getID() == id) {
                encontrado = true;
            } else {
                listaCochesActualizado.add(coche);
            }
        }

        if (encontrado) {
            try (FileOutputStream file1 = new FileOutputStream(NOMBRE_FICHERO);
                 ObjectOutputStream obj1 = new ObjectOutputStream(file1)) {
                 
                for (Coche coche : listaCochesActualizado) {
                    obj1.writeObject(coche);
                }
            } catch (Exception e) {
                System.out.println("Error al borrar el coche.");
                e.printStackTrace();
            }
        }
    }
    
    // Cargar el contador de ID desde el archivo, o inicializarlo si no existe
    private int cargarContadorID() {
        File archivoContador = new File(FICHERO_CONTADOR_ID);
        if (archivoContador.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivoContador))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 9900; // Valor inicial si el archivo no existe
    }
    
    // Guardar el contador de ID en un archivo
    private void guardarContadorID() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FICHERO_CONTADOR_ID))) {
            writer.write(String.valueOf(contadorCoches));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /*
     * Solucion del chatgpt para agregar objetos cuando es un archivo grande. 
     * 
     * public void registrar(Coche c) throws Exception {
        c.setID(++contadorCoches);

        boolean isNewFile = !new File(NOMBRE_FICHERO).exists();
        try (FileOutputStream fichero = new FileOutputStream(NOMBRE_FICHERO, true);
             ObjectOutputStream escritor = isNewFile ? new ObjectOutputStream(fichero) : new AppendableObjectOutputStream(fichero)) {

            escritor.writeObject(c);
            escritor.flush();
            System.out.println("El coche ha sido guardado correctamente: " + c);
        } catch (IOException e) {
            System.out.println("Error al escribir el coche en el archivo.");
            e.printStackTrace();
        }
    }
     * 
     * 
     * 
    // Clase personalizada para evitar agregar encabezado adicional al archivo
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset(); // Evita escribir un nuevo encabezado
        }
    }
*/
}
