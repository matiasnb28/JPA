
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;


public class LibroServicio {
    
     private final LibroDAO dao;
     private final AutorServicio autorServicio;
     private final EditorialServicio editorialServicio;

    public LibroServicio() {
        this.dao = new LibroDAO();
        this.autorServicio = new AutorServicio();
        this.editorialServicio = new EditorialServicio();
    }
    
    public void crear(String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresRestantes, Boolean alta, Integer idAutor, Integer idEditorial) throws Exception  {
        
        Integer ejemplaresPrestados = 0;
        Autor autor = autorServicio.buscarPorId(idAutor);
        Editorial editorial = editorialServicio.buscarPorId(idEditorial);
        Libro l = new Libro();
       
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, idAutor, idEditorial);
       
        l.setIsbn(isbn);
       l.setTitulo(titulo);
       l.setAnio(anio);
       l.setEjemplares(ejemplares);
       l.setEjemplaresPrestados(ejemplaresPrestados);
       l.setEjemplaresRestantes(l.getEjemplares() - l.getEjemplaresPrestados());
       l.setAlta(true);
       l.setAutor(autor);
       l.setEditorial(editorial);
       
        
        dao.guardar(l);
                
    }
    
    public void modificar(String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresRestantes, Integer ejemplaresPrestados, Boolean alta, Integer idAutor, Integer idEditorial) throws Exception {
        
       
        
        Libro l = buscarPorIsbn(isbn);
        validarLibroNull(l);
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, idAutor, idEditorial);
        
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresRestantes(ejemplaresRestantes);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setAlta(alta);
        dao.editar(l);
        
        
    }
    
    public void eliminar(String isbn) throws Exception {
        Libro l = buscarPorIsbn(isbn);
        validarLibroNull(l);
        validarIsbn(isbn);
        dao.eliminar(l);
    }
    
    

    private void validar(String isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer idAutor, Integer idEditorial) throws Exception {
           if(isbn == null || isbn.isEmpty()) {
             throw new Exception("Coloque un valor válido");
        }
        if(titulo == null || titulo.isEmpty()) {
             throw new Exception("Debe indicar el título correctamente");
        }
        if(anio < 0 || anio == null) {
             throw new Exception("Debe indicar el anio correctamente");
        }
        if(ejemplares < 0 || ejemplares == null) {
             throw new Exception("Debe indicar la cantidad de ejemplares correctamente");
        }
        if(ejemplaresPrestados < 0 || ejemplaresPrestados == null) {
             throw new Exception("Debe indicar la cantidad de ejemplares restantes correctamente");
        }
        if(idAutor < 0 || idAutor == null) {
             throw new Exception("Debe indicar un valor válido");
        }
        if(idEditorial < 0 || idEditorial == null) {
             throw new Exception("Debe indicar un valor válido");
        }
    }
    public Libro buscarPorIsbn(String isbn) {
        return dao.buscarPorId(isbn);
    }
    public void validarIsbn(String isbn) throws Exception {
             if(isbn == null || isbn.isEmpty()) {
             throw new Exception("Coloque un valor válido");
        }
    }
    public void validarLibroNull(Libro l) throws Exception {
        if(l == null) {
            throw new Exception("El libro no existe");
        }
    }
    public List<Libro> librosPorTitulo(String titulo) {
    return dao.buscarPorTitulo(titulo);
}
    public List<Libro> buscarPorAutor(String nombre) {
        return dao.buscarPorAutor(nombre);
    }
    public List<Libro> buscarPorEditorial(String nombre) {
        
        return dao.buscarPorEditorial(nombre);
    }
}
