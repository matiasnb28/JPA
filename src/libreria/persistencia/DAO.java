
package libreria.persistencia;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {

    protected final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("JPAPU");
    protected EntityManager em = EMF.createEntityManager();
    
    protected void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    protected void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }
    


}