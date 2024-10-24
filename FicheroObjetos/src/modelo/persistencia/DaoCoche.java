package modelo.persistencia;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.entidad.Coche;

public class DaoCoche {
	private static final String NOMBRE_FICHERO = "coches.dat";
	private static final String FICHERO_TEMPORAL = "ficheroTemporal.dat";
    private static int contadorCoches = 9900; // Contador estático para generar IDs únicos
    private int id; 
	ArrayList<Coche> listaCoches;

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
				ObjectInputStream lector = new ObjectInputStream(fichero);) {
			
			boolean eof = false;
			Coche c;
			while (!eof) {//leemos hasta fin de fichero
				try {
					c = (Coche) lector.readObject();//puede arrojar excepciones de tipo EOFException
												//en caso de que no haya mas objetos que leer
												//es decir, estamos en EOF (End Of File)
					if (id == c.getID()) {
						return c;
					}
					System.out.println(c);
				} catch (EOFException e1) {//si salta esta excepcion, es que hemos llegado a EOF
					eof = true;
					//break;
				} catch (IOException e2) {
					System.out.println("Error al leer los contactos de la agenda");
					System.out.println(e2.getMessage());
				} catch (ClassNotFoundException e3) {
					System.out.println("La clase Contacto no est� cargada en memoria");
					System.out.println(e3.getMessage());
				}
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
		id = ++contadorCoches;

		try (FileOutputStream fichero = new FileOutputStream(NOMBRE_FICHERO, true);
				ObjectOutputStream escritor = new ObjectOutputStream(fichero);) {
			
			c.setID(id);
			escritor.writeObject(c);
			escritor.flush();
			System.out.println(c);
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
		listaCoches = new ArrayList<Coche>();
		
		try (FileInputStream file = new FileInputStream(NOMBRE_FICHERO);
				ObjectInputStream obj = new ObjectInputStream(file)) {
			
			boolean eof = false;
			Coche coche;
			
			while (!eof) {//leemos hasta fin de fichero
				try {
					coche = (Coche) obj.readObject();//puede arrojar excepciones de tipo EOFException
					listaCoches.add(coche);						//en caso de que no haya mas objetos que leer
												//es decir, estamos en EOF (End Of File)
					System.out.println(coche);
					System.out.println("El coche se ha deserializado dao");
					
				} catch (EOFException e1) {//si salta esta excepcion, es que hemos llegado a EOF
					eof = true;
					//break;
				} catch (IOException e2) {
					System.out.println("Error al leer los coches de la lista dao");
					System.out.println(e2.getMessage());
					e2.printStackTrace();
	
				} catch (ClassNotFoundException e3) {
					System.out.println("La clase Coche no esta cargada en memoria dao");
					System.out.println(e3.getMessage());
	
				}
			}
			
			
			} catch (IOException e) {
				System.out.println("No se ha podido abrir la lista de coches dao");
				System.out.println(e.getMessage());
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
		if(listaCoches.isEmpty() || listaCoches == null) {
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
			try(FileOutputStream file1 = new FileOutputStream(NOMBRE_FICHERO);
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
