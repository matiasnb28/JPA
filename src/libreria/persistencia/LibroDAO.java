package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public final class LibroDAO extends DAO {

    public void guardar(Libro objeto) {
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

    public Libro editar(Libro objeto) {
        Libro l = null;
        try {
            conectar();
            em.getTransaction().begin();
            l = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            desconectar();
        }
        return l;

    }

    public void eliminar(Libro objeto) {
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
     public Libro buscarPorId(String isbn) {
        return em.find(Libro.class, isbn);
    }
     public List<Libro> buscarPorTitulo(String titulo) {
         return em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                 .setParameter("titulo", "%" + titulo + "%")
                 .getResultList();
     }
     public List<Libro> buscarPorAutor(String nombre) {
         return em.createQuery("SELECT n FROM Libro n WHERE n.autor.nombre LIKE :nombre")
                 .setParameter("nombre", "%" + nombre + "%")
                 .getResultList();
     }
     public List<Libro> buscarPorEditorial(String nombre) {
         return em.createQuery("SELECT n FROM Libro n WHERE n.editorial.nombre LIKE :nombre")
                 .setParameter("nombre", "%" + nombre + "%")
                 .getResultList();
     }
}
