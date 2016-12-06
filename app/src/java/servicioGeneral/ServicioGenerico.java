package servicioGeneral;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class ServicioGenerico <Entity, Key> implements ServicioFactory<Entity, Key>{

    @PersistenceContext
    protected EntityManager EM;
    
    @Override
    public void guardar(Entity entidad) {
        EM.persist(entidad);
    }

    @Override
    public Entity consultarXId(Class<Entity> c, Key k) {
        return EM.find(c, k);
    }

    @Override
    public void actualizar(Entity ent) {
        EM.merge(ent);
    }

    @Override
    public void eliminar(Class c, Key k) {
        Entity e = (Entity)EM.getReference(c, k);
        EM.remove(e);
    }

    @Override
    public List<Entity> listar(Class c) {
        StringBuilder st =  new StringBuilder();
        List<Entity> l;
        
        st.append("SELECT o FROM ");
        st.append(c.getSimpleName());
        st.append(" o ");
        
        Query q = EM.createQuery(st.toString());
        l = q.getResultList();
        return l;
    }
    
}
