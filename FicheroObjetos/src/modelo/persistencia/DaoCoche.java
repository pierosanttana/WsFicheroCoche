package modelo.persistencia;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

import modelo.entidad.Coche;

public class DaoCoche {
	private static final String NOMBRE_FIHERO = "coches.dat";

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
		Coche coche = null;
		try (FileInputStream fichero = new FileInputStream(NOMBRE_FIHERO);
				ObjectInputStream lector = new ObjectInputStream(fichero);) {
			coche = (Coche) lector.readObject();

			if (id == coche.getID()) {
				return coche;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void registrar(Coche c) throws Exception {
		File file = new File (NOMBRE_FIHERO);
		
		
	}

}
