/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Materia;
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
    
    private Carrera car;
    
    private Estudiante est;
    
    private List<Materia> listaMateria;

    public ControladorPrincipal() {
       usu = new Usuario();
    }
    
    @PostConstruct
    private void init(){
        verificarSesion();
    }
    
    public void verificarSesion(){
         Object o = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogin");
         Usuario u = (Usuario) o;
         usu = su.consultarXId(Usuario.class, u.getIdUsuario());
         if(usu != null){
             est = se.estudianteUsuario(usu);
             car = null;
             car = est.getCarrera();
             listaMateria = scm.materiasXCarrera(car); 
         }
    }
    
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }

    public Carrera getCar() {
        return car;
    }

    public void setCar(Carrera car) {
        this.car = car;
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }
}