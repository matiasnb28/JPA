package libreria.persistencia;

import java.util.List;
import libreria.entidades.Editorial;

public final class EditorialDAO extends DAO {

    public void guardar(Editorial objeto) {
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

    public Editorial editar(Editorial objeto) {
        Editorial e = null;
        try {
            conectar();
            em.getTransaction().begin();
            e = em.merge(objeto);
            em.getTransaction().commit();
            desconectar();
            return e;
        } catch (Exception t) {
            System.out.println(t.getMessage());
        } finally {
            desconectar();
        }
        return e;

    }

    public void eliminar(Editorial objeto) {
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

    public Editorial buscarPorId(Integer id) {
        return em.find(Editorial.class, id);
    }

    public List<Editorial> listarTodos() {
        return em.createQuery("SELECT e FROM Editorial e").getResultList();
    }

}
