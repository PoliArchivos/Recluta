/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author USER
 */
@Entity
@NamedQuery(name = "Carrera.carreraXnombre",
            query = "SELECT c FROM Carrera c WHERE c.nombreCarrera = :carrera")
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarrera;
    
    @Column
    @NotNull
    private String nombreCarrera;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Facultad idFacultad;
    
    public Carrera(){}
    
    public Carrera(int id, String nombre, Facultad f){
        this.nombreCarrera = nombre;
        this.idFacultad = f;
        this.idCarrera = id;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }
    
}
