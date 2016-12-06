package servicioGeneral;

import entidades.Carrera;
import entidades.CarreraMateria;
import entidades.Materia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioCarreraMateria extends ServicioGenerico<Materia, Integer>{
    
    public List<Materia> materiasXCarrera(Carrera c){
        TypedQuery<Materia> jpql = EM.createNamedQuery("CarreraMateria.materiaXcarrera", Materia.class);
        jpql.setParameter("carrera", c.getIdCarrera());
        
        try{
            return jpql.getResultList();
        }catch(NoResultException e){
            return null;
        }    
    }
}
