
package libreria.servicios;

import java.util.List;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;


public class AutorServicio {
    
    private final AutorDAO dao;

    public AutorServicio() {
        dao = new AutorDAO();
    }
    
    public void crear(String nombre) throws Exception {
        
        Autor a = new Autor();
        validar(nombre);
        a.setNombre(nombre);
        a.setAlta(true);
        
        
        dao.guardar(a);
                
    }
    
    public void modificar(Integer id, String nombre, Boolean alta) {
        
        Autor a = buscarPorId(id);
        a.setNombre(nombre);
        a.setAlta(alta);
        
        dao.editar(a);
        
    }
    
    public void eliminar(Integer id) {
        Autor a = buscarPorId(id);
        
        dao.eliminar(a);
    }
    
    public List<Autor> listarTodos() {
        return dao.listarTodos();
    }
    
    public Autor buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    
    public void validar(String nombre) throws Exception  {
        
       
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("Ingrese un nombre válido");
        }
        
    }
    public Autor buscarPorNombre(String nombre) throws Exception {
        if(dao.buscarPorNombre(nombre) == null) {
            throw new Exception("Flaco ese nombre no esite");
        }
         return dao.buscarPorNombre(nombre);
    }
    
}
