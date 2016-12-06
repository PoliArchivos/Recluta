/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicioGeneral;

import java.util.List;

/**
 *
 * @author USER
 */
public interface ServicioFactory <Entity, Key>{
    
    void guardar(Entity entidad);
    
    Entity consultarXId(Class<Entity> c, Key k);
    
    void actualizar(Entity ent);
    
    void eliminar(Class c, Key k);
    
    List<Entity> listar(Class c);
}