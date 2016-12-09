package servicioGeneral;

import entidades.Carrera;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioCarrera extends ServicioGenerico<Carrera, Integer>{
    
    public Carrera carreraXnombre(Carrera c){
        TypedQuery<Carrera> jpql = EM.createNamedQuery("Carrera.carreraXnombre", Carrera.class);
        jpql.setParameter("carrera", c.getNombreCarrera());
        
        try{
            return jpql.getSingleResult();   
        }catch(NoResultException e){
            return null;
        }    
    }
}
