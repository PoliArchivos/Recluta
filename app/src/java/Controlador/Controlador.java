/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Facultad;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import servicioGeneral.ServicioFacultad;

/**
 *
 * @author USER
 */
@Named(value = "controlador")
@ViewScoped
public class Controlador implements Serializable {

    @EJB
    private ServicioFacultad sf;
    
    private Facultad fac;
    
    private int id;
    
    private List<Facultad> listaFac;
    
    private String nombreViejo;

    public Controlador() {
        fac = new Facultad();
    }
    
    @PostConstruct
    public void inicio(){
        listar();
    }
    
    private void listar(){
        listaFac = sf.listar(Facultad.class);
    }
    
    public void registrar() throws IOException{
        Facultad f = sf.facultadXnombre(fac);
        if(f == null){
        fac.setIdFacultad(id);
        sf.guardar(fac);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Se registro exitosamente"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().getExternalContext().redirect("GestionFacultad.xhtml");
        fac = new Facultad();
        }else{
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ya hay una facultad con el nombre "+fac.getNombreFacultad()));  
        }
    }

    private void gestion_id(){
        id = listaFac.size();
    }
    
    public void seleccionarFacultad(Facultad facultad){
        this.fac = facultad;
        this.nombreViejo = facultad.getNombreFacultad();
    }
    
    public void modificarFacultad(Facultad fa) {
        Facultad f = sf.facultadXnombre(fac);
        if (f == null) {
            sf.actualizar(fac);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("el nombre de la facultad fue actualizado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ya hay una facultad con el nombre " + fac.getNombreFacultad() + ", por favor ingrese otro"));
            fac.setNombreFacultad(nombreViejo);
        }
    }
    
    public void elminarFacultad(Facultad fc) throws IOException{
        Facultad facultad = sf.facultadXnombre(fc);
        if (facultad != null) {
            sf.eliminar(Facultad.class, facultad.getIdFacultad());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la facultad: " + facultad.getNombreFacultad().toUpperCase() + " fue eliminado exitosamente"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().getExternalContext().redirect("GestionFacultad.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la facultad: " + fc.getNombreFacultad() + " no se encuentra registrado en el sistema"));
        }
    }
    
    public Facultad getFac() {
        return fac;
    }

    public void setFac(Facultad fac) {
        this.fac = fac;
    }

    public List<Facultad> getListaFac() {
        return listaFac;
    }

    public void setListaFac(List<Facultad> listaFac) {
        this.listaFac = listaFac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreViejo() {
        return nombreViejo;
    }

    public void setNombreViejo(String nombreViejo) {
        this.nombreViejo = nombreViejo;
    }
}