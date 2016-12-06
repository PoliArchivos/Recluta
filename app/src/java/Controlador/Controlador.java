/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Facultad;
import java.io.Serializable;
import java.util.List;
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
    
    public void registrar(){
        
        fac.setIdFacultad(id);
        sf.guardar(fac);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("SE registro exitosamente"));
        
        fac = new Facultad();
    }

    private void gestion_id(){
        id = listaFac.size();
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

    
}
