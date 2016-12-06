package servicioGeneral;

import entidades.Carrera;
import entidades.Roles;
import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioRoles extends ServicioGenerico<Roles, Integer>{
    
    public Roles rolesXNombre(Roles r){
        TypedQuery<Roles> jpql = EM.createNamedQuery("Roles.nombre", Roles.class);
        jpql.setParameter("nombre", r.getNombreRol());
        
        try{
            return jpql.getSingleResult();
        }catch(NoResultException e){
            return null;
        }    
    }
    
}
