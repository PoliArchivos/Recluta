package Controlador;
import entidades.Carrera;
import entidades.Facultad;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import servicioGeneral.ServicioCarrera;
import servicioGeneral.ServicioFacultad;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ControladorCarrera implements Serializable{

    
    @EJB
    private ServicioCarrera sc;
    
    private Carrera ca;
    
    private Facultad facultad;
    
    private List<Carrera> listaCarrera;
    
    @EJB
    private ServicioFacultad sf;
    
    
    /**
     * Creates a new instance of ControladorCarrera
     */
    public ControladorCarrera() {
        ca = new Carrera();
        facultad = new Facultad();
    }
    
    public void registrar(){
        Facultad f = sf.consultarXId(Facultad.class, facultad.getIdFacultad());
        if(f != null){
       ca.setIdFacultad(f);
       sc.guardar(ca);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registro exitosamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("facultad nula"));
        }
    }

    @PostConstruct
    public void inicio(){
        listar();
    }
    
    private void listar(){
        listaCarrera = sc.listar(Carrera.class);
    }
    
    
    public Carrera getCa() {
        return ca;
    }

    public void setCa(Carrera ca) {
        this.ca = ca;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public List<Carrera> getListaCarrera() {
        return listaCarrera;
    }

    public void setListaCarrera(List<Carrera> listaCarrera) {
        this.listaCarrera = listaCarrera;
    }
    
   
    
}
