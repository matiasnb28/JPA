package libreria;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;

public class Libreria {

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        LibroServicio libroServicio = new LibroServicio();
        AutorServicio autorServicio = new AutorServicio();
        EditorialServicio editorialServicio = new EditorialServicio();
        int opcion = 0;
        System.out.println("Bienvenido al menú de librería");
        System.out.println("Desea agregar un autor? S/N");
        String opcion1 = leer.next();
        if(opcion1.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nombre de un Autor");
        String nombreAutor = leer.next();
        autorServicio.crear(nombreAutor);
        }
        System.out.println("Desea agregar una editorial? S/N");
        String opcion2 = leer.next();
        if(opcion2.equalsIgnoreCase("s")) {
        System.out.println("Ingrese el nombre de una editorial");
        String nombreEditorial = leer.next();
        editorialServicio.crear(nombreEditorial);
        }
        do {
            System.out.println("Por favor elija la opción que desea realizar");
            System.out.println("1- Cargar los datos de un libro");
            System.out.println("2- Buscar un autor por su nombre");
            System.out.println("3- Buscar un libro por su ISBN");
            System.out.println("4- Buscar un libro por su título");
            System.out.println("5- Buscar un libro por el nombre de su autor");
            System.out.println("6- Buscar un libro por el nombre de su editorial");
            System.out.println("7- Salir");
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el ISBN del libro");
                    String isbn = leer.next();
                    System.out.println("Ingrese el titulo del libro");
                    String titulo = leer.next();
                    System.out.println("Ingrese el anio del libro");
                    Integer anio = leer.nextInt();
                    System.out.println("Ingrese la cantidad de ejemplares");
                    Integer ejemplares = leer.nextInt();
                    System.out.println("Ingrese la cantidad de ejemplares prestados");
                    Integer ejemplaresPrestados = leer.nextInt();
                    System.out.println("Ingrese el ID del autor");
                    Integer idAutor = leer.nextInt();
                    System.out.println("Ingrese el ID de la editorial");
                    Integer idEditorial = leer.nextInt();
                    libroServicio.crear(isbn, titulo, anio, ejemplares, ejemplaresPrestados, true, idAutor, idEditorial);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del autor");
                    String nombreAutor2 = leer.next();
                    Autor autores = autorServicio.buscarPorNombre(nombreAutor2);
                    System.out.println(autores);
                    
                        break;
                case 3:
                    System.out.println("Ingrese el ISBN del libro que desea buscar");
                    String isbnLibro = leer.next();
                    Libro libroPorIsbn = libroServicio.buscarPorIsbn(isbnLibro);
                    System.out.println(libroPorIsbn);
                    break;
                case 4:
                    System.out.println("Ingrese el título del libro que desea buscar");
                    String tituloLibro = leer.next();
                    List<Libro> libroPorTitulo = libroServicio.librosPorTitulo(tituloLibro);
                    
                    for (Libro libros : libroPorTitulo) {
                        System.out.println(libros.toString());
                    }
                        break;
                case 5:
                    System.out.println("Ingrese el nombre del autor del libro que desea buscar");
                    String autorLibro = leer.next();
                    List<Libro> librosPorAutor = libroServicio.buscarPorAutor(autorLibro);
                    for (Libro libros : librosPorAutor) {
                        System.out.println(libros.toString());
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el nombre de la editorial del libro que desea buscar");
                    String editorialLibro = leer.next();
                    List<Libro> librosPorEditorial = libroServicio.buscarPorEditorial(editorialLibro);
                    for (Libro libros : librosPorEditorial) {
                        System.out.println(libros.toString());
                    }
            }
        } while (opcion != 7);
    }

}
