package servicioGeneral;

import entidades.Carrera;
import entidades.CarreraMateria;
import entidades.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioCarreraMateria extends ServicioGenerico<CarreraMateria, Integer>{
    
    public List<Materia> materiasXCarrera(Carrera c){
        List<Materia> materias = new ArrayList<>();
        List<CarreraMateria> ca;
        Materia m;
        TypedQuery<CarreraMateria> jpql = EM.createNamedQuery("CarreraMateria.materiaXcarrera", CarreraMateria.class);
         System.err.println("ide carrera para jpql : " + c.getIdCarrera());
        jpql.setParameter("carrera", c.getIdCarrera());
        
        try{
            ca = jpql.getResultList();
            for (CarreraMateria carreraMateria : ca) {
                m = carreraMateria.getIdMateria();
                materias.add(m);
                m = new Materia();
            }
            return materias;
        }catch(NoResultException e){
            return null;
        }    
    }
}
