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
    
    public List<CarreraMateria> materiasXCarrera(Carrera c){
        TypedQuery<CarreraMateria> jpql = EM.createNamedQuery("CarreraMateria.materiaXcarrera", CarreraMateria.class);
         System.err.println("ide carrera para jpql : " + c.getIdCarrera());
        jpql.setParameter("carrera", c.getIdCarrera());
        List<CarreraMateria> resultList = jpql.getResultList();
        try{
            return resultList;
        }catch(NoResultException e){
            return null;
        }    
    }
}
