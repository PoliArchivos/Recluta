package Controlador;

import entidades.Carrera;
import entidades.CarreraMateria;
import entidades.Materia;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import servicioGeneral.ServicioCarrera;
import servicioGeneral.ServicioCarreraMateria;
import servicioGeneral.ServicioMateria;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ControladorCarreraMateria implements Serializable{

    
    @EJB
    private ServicioCarreraMateria scm;
    
    @EJB
    private ServicioCarrera sc;
     
    @EJB
    private ServicioMateria sm;
    
    private CarreraMateria cm;
    
    private Carrera ca;
    
    private Materia ma;
    private int idu;
    
    /**
     * Creates a new instance of ControladorCarreraMateria
     */
    public ControladorCarreraMateria() {
        cm = new CarreraMateria();
        ca = new Carrera();
        ma = new Materia();
    }
    
    public void registrarCarreraMateria(){
        
        Carrera c = sc.consultarXId(Carrera.class, ca.getIdCarrera());
        Materia m = sm.consultarXId(Materia.class, ma.getIdMateria());
        if(c != null && m != null){
           cm.setIdCarrera(ca);
           cm.setIdMateria(ma);
           idu = (int) (Math.random() * 10000) + 1;
           cm.setIdCarreraMateria(idu);
           scm.guardar(cm);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registro exitosamente"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("facultad nula"));
        }
    
    }

    public CarreraMateria getCm() {
        return cm;
    }

    public void setCm(CarreraMateria cm) {
        this.cm = cm;
    }

    public Carrera getCa() {
        return ca;
    }

    public void setCa(Carrera ca) {
        this.ca = ca;
    }

    public Materia getMa() {
        return ma;
    }

    public void setMa(Materia ma) {
        this.ma = ma;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
    
}
