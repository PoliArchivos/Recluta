package servicioGeneral;

import entidades.Estudiante;
import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioEstudiante extends ServicioGenerico<Estudiante, Integer>{
    
    public Estudiante estudianteUsuario(Usuario u){
        TypedQuery<Estudiante> jpql = EM.createNamedQuery("Estudiante.usuario", Estudiante.class);
        jpql.setParameter("usuario", u.getIdUsuario());
        
        try{
            return jpql.getSingleResult();
        }catch(NoResultException e){
            return null;
        }    
    }
}
