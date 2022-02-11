
package libreria.servicios;

import java.util.List;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;


public class EditorialServicio {
     private final EditorialDAO dao;

    public EditorialServicio() {
        dao = new EditorialDAO();
    }
    
    public void crear(String nombre) throws Exception {
        
        Editorial e = new Editorial();
        validar(nombre);
        e.setNombre(nombre);
        e.setAlta(true);
        
        
        dao.guardar(e);
                
    }
    
    public void modificar(Integer id, String nombre, Boolean alta) throws Exception {
        
        Editorial e = buscarPorId(id);
        validarEditorialNull(e);
        validar(nombre);
        e.setNombre(nombre);
        e.setAlta(alta);
        
        dao.editar(e);
        
    }
    
    public void eliminar(Integer id) throws Exception {
        Editorial e = buscarPorId(id);
        validarEditorialNull(e);
        dao.eliminar(e);
    }
    
    public List<Editorial> listarTodos() {
        return dao.listarTodos();
    }
    
    public Editorial buscarPorId(Integer id) {
        return dao.buscarPorId(id);
    }
    public void validar(String nombre) throws Exception {
        
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("Ingrese un nombre válido");
        }
    }
    public void validarEditorialNull(Editorial e) throws Exception {
        if(e == null) {
            throw new Exception("Ingrese una editorial válida");
        }
    }
}
