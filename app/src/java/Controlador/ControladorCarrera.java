package Controlador;

import entidades.Carrera;
import entidades.Facultad;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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
public class ControladorCarrera implements Serializable {

    @EJB
    private ServicioCarrera sc;

    private Carrera ca;

    private Facultad facultad;

    private List<Carrera> listaCarrera;

    @EJB
    private ServicioFacultad sf;

    private int idu;

    private String nombreViejo;

    private Facultad fa;
    
    private String nombre;

    /**
     * Creates a new instance of ControladorCarrera
     */
    public ControladorCarrera() {
        ca = new Carrera();
        facultad = new Facultad();
    }

    public void registrar() {
        Facultad f = sf.consultarXId(Facultad.class, facultad.getIdFacultad());
        if (f != null) {
            ca.setIdFacultad(f);
            idu = (int) (Math.random() * 10000) + 1;
            ca.setIdCarrera(idu);
            sc.guardar(ca);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registro exitosamente"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("facultad nula"));
        }
    }

    @PostConstruct
    public void inicio() {
        listar();
    }

    private void listar() {
        listaCarrera = sc.listar(Carrera.class);
    }

    public void seleccionarCarrera(Carrera c) {
        this.ca = c;
        this.nombreViejo = c.getNombreCarrera();
        this.fa = c.getIdFacultad();
    }

    public void modificarCarrera(Carrera c) {
        Facultad f = sf.consultarXId(Facultad.class, facultad.getIdFacultad());
        Carrera car = sc.carreraXnombre(ca);
        if(fa == null){
            fa = f;
        }
        if (car == null || !Objects.equals(fa.getIdFacultad(), f.getIdFacultad())) {
            
            ca.setIdFacultad(f);
            sc.actualizar(ca);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se actualizo la carrera"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ya hay una carrera con el nombre " + ca.getNombreCarrera()));
            ca.setNombreCarrera(nombreViejo);
        }
    }
    
    public void eliminarCarrera(Carrera c) throws IOException{
        Carrera car = sc.consultarXId(Carrera.class, c.getIdCarrera());
        if (car != null) {
            sc.eliminar(Carrera.class, car.getIdCarrera());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la carrera: " + car.getNombreCarrera().toUpperCase() + " fue eliminada exitosamente"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GestionCarrera.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la carrera: " + c.getNombreCarrera() + " no se encuentra registrado en el sistema"));
        }
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

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public String getNombreViejo() {
        return nombreViejo;
    }

    public void setNombreViejo(String nombreViejo) {
        this.nombreViejo = nombreViejo;
    }

    public Facultad getFa() {
        return fa;
    }

    public void setFa(Facultad fa) {
        this.fa = fa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
