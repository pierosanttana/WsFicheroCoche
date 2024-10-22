Queremos hacer un programa que trabaje con objetos de tipo Coche y los persista en un fichero. 
El coche tendrá un id, una marca, un modelo y un tipo de motor que podrá ser gasolina, diésel o de hidrogeno. 

El programa mostrará el siguiente menú. 

Introducir coche
Obtener coche por ID
Borrar coche por ID 
Listar todos los coches
Salir 
La opción 1 pedirá todos los datos del coche y lo meterá en un fichero binario 
(menos el ID, el ID no se pedirá al usuario). 

La opción 2 de obtendrá un cochea partir de un ID 

La opción 3 borrara un coche por ID 

La opción 4 mostrará todos los coches. 

La opción 5 saldrá del programa. 

Consideraciones: 

Cada vez que se guarde un coche se añadirá a un fichero binario, al final de todos los demás coches.

No puede haber coches con la marca y el modelo vacíos en el fichero (regla de negocio, labor del gestor)

El ID lo deberá generar de manera automática el DAO

No se admiten números negativos para borrar coches (regla de negocio, labor del gestor)

Se debe usar el modelo de las tres capas.

Se debe hacer por parejas o tríos, repartiendo las tareas y usando Github.

Besos y abrazos.

------------------------------------------------------------------------------------------------------


A ver en el dao esta todos los metodos y alguno que otro esta empezado, 
En el gestor estan los metodo con sus nombres pero no hay ninguno hecho.
La interfaz ya la hacemos de ultima que se tarda poco, espero que no mande hacer pruebas unitarias 
aunque estaría bien hacerlas para practicar
Falta la documnentación de los metodos pero eso en 5 min esta hecho.
