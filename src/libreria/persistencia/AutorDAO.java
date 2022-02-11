package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;

public final class AutorDAO extends DAO {

    public void guardar(Autor objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public Autor editar(Autor objeto) {
        Autor a = null;
        try {
            conectar();
            em.getTransaction().begin();
            a = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return a;

    }

    public void eliminar(Autor objeto) {
        try {
            conectar();
            em.getTransaction().begin();
            em.remove(objeto);
            em.getTransaction().commit();
            desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
    }

    public Autor buscarPorId(Integer id) {
        return em.find(Autor.class, id);
    }

    public List<Autor> listarTodos() {
        return em.createQuery("SELECT a FROM Autor a").getResultList();
    }
    
   
public Autor buscarPorNombre(String nombre) {
    //List<Autor> autores = null;
    try{
        
        return  (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getSingleResult();
    }catch(Exception e) {
        return null;
    }
    }
    
}
