package jpa;

import java.awt.Image;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import props.Arma;

/**
 *
 * @author mor
 * @version 130416
 */
public class ServicioArma {
    
    private EntityManager em;
    
    public ServicioArma(EntityManager em) {
        this.em = em;
    }
    
    public Arma crear(String n, int atk, int def, Image img) {
        Arma a = new Arma();
        a.setNombre(n);
        a.setAtk(atk);
        a.setDef(def);
        a.setImagen(img);
        em.persist(a);
        return a;
    }
    
    public void eliminar(long id) {
        Arma a = buscar(id);
        if (!a.isNull()) {
            em.remove(a);
        }
    }
    
    public Arma buscar(long id) {
        return em.find(Arma.class, id);
    }
    
    public Arma cambiarStats(long id, int atk, int def, Image img) {
        Arma a = em.find(Arma.class, id);
        if (!a.isNull()) {
            a.setAtk(atk);
            a.setDef(def);
            a.setImagen(img);
        }
        return a;
    }
    
    public List<Arma> listaPersonajes() {
        TypedQuery<Arma> query = em.createQuery(
                "SELECT e FROM ARMA e", Arma.class);
        return query.getResultList();
    }
    
}
