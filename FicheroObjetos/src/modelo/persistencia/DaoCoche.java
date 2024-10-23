package modelo.persistencia;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.entidad.Coche;

public class DaoCoche {
	private static final String NOMBRE_FIHERO = "coches.dat";
	private static final String FICHERO_TEMPORAL = "ficheroTemporal.dat";

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

	/**
	 * Metodo que registra un coche pasado por parametro.Comprueba la existencia
	 * del fichero en caso de que no exista devuelve una excepcion
	 * @param c coche que pasamos por parametro
	 * @throws Exception
	 */
	public void registrar(Coche c) throws Exception {
		File file = new File(NOMBRE_FIHERO);
		if (!file.exists()) {
			throw new Exception("Fichero no existe XD");

		}
		try (FileOutputStream fichero = new FileOutputStream(NOMBRE_FIHERO, true);
				ObjectOutputStream escritor = new ObjectOutputStream(fichero);) {
			escritor.writeObject(c);
			System.out.println("El objeto ha sido guardado correctamente");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que genera un ID y delvuelve ese ID
	 * @return ID que devuelve
	 * @throws Exception
	 */
	public long generadorId() throws Exception {

		return 0;
	}

	/**
	 * Metodo que lee el fichero y lo almacena en un arrayList
	 * @return devuelve un arrayList de coches
	 * @throws Exception
	 */
	public ArrayList<Coche> getListaCoches() throws Exception {
		ArrayList<Coche> listaCoches = null;
		;
		try (FileInputStream file = new FileInputStream(NOMBRE_FIHERO);
				ObjectInputStream obj = new ObjectInputStream(file)) {
			Coche coche = (Coche) obj.readObject();
			System.out.println("El coche se ha deserializado");
			listaCoches.add(coche);
		} catch (Exception e) {
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
		ArrayList<Coche>listaCoches = getListaCoches();
		if(listaCoches.isEmpty() || listaCoches== null) {
			// Devolvemos algo no se el que
		}
		boolean encontrado = false;
		for(Coche coche :listaCoches) {
			if(coche.getID() == id) {
				encontrado = true;
				break;
			}
		}
		if(encontrado) {
			ArrayList<Coche>listaCochesActualizado = new ArrayList<Coche>();
			
			for(Coche coche: listaCoches) {
				if(coche.getID()!= id) {
					listaCochesActualizado.add(coche);
				}
			}
			try(FileOutputStream file1 = new FileOutputStream(NOMBRE_FIHERO);
				ObjectOutputStream obj1 = new ObjectOutputStream(file1)){
				for(Coche coche :listaCochesActualizado) {
					obj1.writeObject(coche);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
