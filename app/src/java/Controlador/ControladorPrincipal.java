/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Carrera;
import entidades.CarreraMateria;
import entidades.Estudiante;
import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import servicioGeneral.ServicioCarreraMateria;
import servicioGeneral.ServicioEstudiante;
import servicioGeneral.ServicioUsuario;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ControladorPrincipal implements Serializable{
    
    @EJB
    private ServicioUsuario su;
    
    @EJB
    private ServicioCarreraMateria scm;
    
    @EJB
    private ServicioEstudiante se;
    
    private Usuario usu;
    
    private List<CarreraMateria> listaMateria;

    public ControladorPrincipal() {
       usu = new Usuario();
    }

    public void verificarSesion(){
         Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
         Usuario u = (Usuario) o;
         System.err.println("Usuario inicial");
         System.err.println(u.getContrasena()+ " " + u.getIdUsuario()+ " " + 
                                    u.getRol()+ " " + u.getUsuario());
         usu = su.consultarXId(Usuario.class, u.getIdUsuario());
         System.err.println("Usuario Sec");
         System.err.println(usu.getContrasena()+ " " + usu.getIdUsuario()+ " " + 
                                    usu.getRol()+ " " + usu.getUsuario());
         if(usu != null){
             System.err.println("Usuario no esta nulo");
             Estudiante e = se.estudianteUsuario(usu);
             Carrera  c = null;
             if(e != null) { 
                System.err.println("estudiante " + e.getNombre());
                 c = e.getCarrera();
             }
             if (c != null) { 
                   System.err.println("Carrera " + c.getNombreCarrera() +" "+ c.getIdCarrera());
                   listaMateria = scm.materiasXCarrera(c); 
                   System.err.println("materia " + listaMateria.get(0).getIdCarrera().getNombreCarrera() + " " + listaMateria.get(0).getIdMateria().getIdMateria());
             }
         }
    }

    @PostConstruct
    public void inicio(){
        verificarSesion();
    }
    
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<CarreraMateria> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<CarreraMateria> listaMateria) {
        this.listaMateria = listaMateria;
    }

    
 
}
