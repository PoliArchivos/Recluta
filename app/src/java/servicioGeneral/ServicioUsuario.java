package servicioGeneral;

import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author USER
 */
@Stateless
public class ServicioUsuario extends ServicioGenerico<Usuario, Integer>{
    
    public Usuario loginUsuario(Usuario u){
        TypedQuery<Usuario> jpql = EM.createNamedQuery("Usuario.login", Usuario.class);
        jpql.setParameter("usuario", u.getUsuario());
        jpql.setParameter("contrasena", u.getContrasena());
        
        try{
            return jpql.getSingleResult();
        }catch(NoResultException e){
            return null;
        }    
    }
    
    
    public Usuario consultarUsuario(Usuario u){
        TypedQuery<Usuario> jpql = EM.createNamedQuery("Usuario.usuario", Usuario.class);
        jpql.setParameter("usuario", u.getUsuario());
        
        try{
            return jpql.getSingleResult();
        }catch(NoResultException e){
            return null;
        }    
    }
    
    
}
