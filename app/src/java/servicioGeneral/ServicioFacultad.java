package servicioGeneral;

import entidades.Facultad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioFacultad extends ServicioGenerico<Facultad, Integer>{
   
    public Facultad facultadXnombre(Facultad f){
        TypedQuery<Facultad> jpql = EM.createNamedQuery("Facultad.facultadXnombre", Facultad.class);
        jpql.setParameter("facultad", f.getNombreFacultad());
        
        try{
            return jpql.getSingleResult();   
        }catch(NoResultException e){
            return null;
        }    
    }
    
}
