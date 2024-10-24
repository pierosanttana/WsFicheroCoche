package interfaz;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.TipoMotor;
import modelo.negocio.GestorCoche;

public class InterfazCoche {
	private GestorCoche gc;
	private Scanner scString = new Scanner(System.in);
	private Scanner sc = new Scanner(System.in);

	public void iniciarAplicacion() {
		System.out.println("========================================");
		System.out.println("        ¡Bienvenido a la aplicación!     ");
		System.out.println("========================================");
		System.out.println();

		int opcion = 0;
		do {
			opcion = menu();
			switch (opcion) {
			case 1:
				registrarCoche();
				break;
			case 2:
				buscarCocheById();
				break;
			case 3:
				mostrarCoches();
				break;
			case 4:
				borrarCocheById();
				break;
			case 0:
				System.out.println("Fin del programa");
				break;
			default:
				System.out.println("Opcion incorrecta wachiiin");
			}
		} while (opcion != 0);
	}

	private int menu() {
		boolean correcto = false;
		String opcion = null;
		int iOpcion = 0;
		while (!correcto) {
			System.out.println("              MENÚ PRINCIPAL");
			System.out.println("========================================");
			System.out.println("| 1. Registar Coche                    |");
			System.out.println("| 2. Buscar Coche por ID               |");
			System.out.println("| 3. Listar Coches                     |");
			System.out.println("| 4. Borrar Coche                      |");
			System.out.println("| 0. Salir del prograna                |");
			System.out.println("========================================");
			System.out.print("Selecciona una opción: ");
			opcion = scString.nextLine();
			try {
				iOpcion = Integer.parseInt(opcion);
				if (iOpcion >= 0 && iOpcion <= 4) {
					correcto = true;
				} else {
					System.out.println("Opcion incorrecta");
				}
			} catch (Exception e) {
				System.out.println("Felix no cuela ya");
			}
		}
		return iOpcion;
	}

	private Coche pedirDatos() {

		Coche coche = null;
		System.out.println("Datos del Coche");
		System.out.println("Nombre:");
		String nombre = scString.nextLine();
		System.out.println("Marca:");
		String marca = scString.nextLine();
		System.out.println("Modelo:");
		String modelo = scString.nextLine();
		System.out.println("Elige el tipo de motor:");

		boolean flag = false;

		while (!flag) {
			for (TipoMotor t : TipoMotor.values()) {
				System.out.println(t.ordinal() + ": " + t);
			}
			try {
				int nTipo = sc.nextInt(); // Leer la elección del usuario

				// Validar que el número está en el rango correcto
				if (nTipo >= 0 && nTipo < TipoMotor.values().length) {
					coche = new Coche(nombre, marca, modelo, TipoMotor.values()[nTipo]);
					flag = true; // Salir del bucle cuando se selecciona un motor válido
				} else {
					System.out.println("Número de motor incorrecto. Intenta de nuevo.");
				}
			} catch (InputMismatchException e) {
				// Manejar si el usuario introduce algo que no es un número
				System.out.println("Entrada no válida. Debes ingresar un número.");
				sc.next(); // Limpiar el buffer del scanner
			}
		}

		return coche;
	}

	private void registrarCoche() {
		gc = new GestorCoche();
		Coche coche = pedirDatos();
		int respuesta = gc.guardarCoche(coche);
		switch (respuesta) {
		case 1:
			System.out.println("Usuario en blanco o con solo espacios en blanco");
			break;
		case 2:
			System.out.println("Password en blanco o con solo espacios en blanco");
			break;
		case 3:
			System.out.println("Usuario guardado con exito!! :) :)");
			break;
		case 666:
			System.out.println("Error de acceso. Intentelo mas tarde. Codigo 666");
			break;
		}
	}

	private void buscarCocheById() {
		gc = new GestorCoche();
		System.out.println("Ingresa el ID del Coche que deseas buscar:");
		long id = sc.nextLong();
		Coche cBuscar = gc.buscarById(id);

		if (cBuscar == null) {
			System.out.println("El coche no existe");
		} else {
			System.out.println(cBuscar);
		}

	}

	private void borrarCocheById() {
		gc = new GestorCoche();
		System.out.println("Ingresa el ID del Coche que deseas borrar:");
		long id = sc.nextLong();
		int cBorrar = gc.borrarCoche(id);

		switch (cBorrar) {
		case 0 -> System.out.println("El coche no existe");
		case 1 -> System.out.println("El coche se ha borrado correctamente");

		default -> System.err.println("Ha ocurrido un ERROR");
		}

		/*
		 * switch (cBorrar) { case 0: System.out.println("El coche no existe"); break;
		 * case 1: System.out.println("El coche se ha borrado correctamente"); break;
		 * case 666: System.err.println("Ha ocurrido un ERROR"); break; default: break;
		 * }
		 */

	}

	private void mostrarCoches() {
		gc = new GestorCoche();
		ArrayList<Coche> listaCoches = null;
		try {
			listaCoches = gc.mostrarListaCoches();
			System.out.println("========================================");
			System.out.println("         Lista de Coches");
			System.out.println("========================================");

			int contador = 1;
			if (listaCoches.isEmpty()) {
				System.out.println(" No hay coches en la lista.");
			} else {
				for (Coche coche : listaCoches) {
					System.out.println(" Coche #" + contador + ":");
					System.out.println(coche);
					System.out.println("----------------------------------------");
					contador++;
				}
			}

			System.out.println("========================================");
		} catch (Exception e) {
			System.out.println("Ha sucedido algún error en la consulta, inténtelo de nuevo más tarde");
			System.out.println("Error: " + e.getClass());
		}

	}

}
