/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author USER
 */
@Entity
@NamedQuery(name = "CarreraMateria.materiaXcarrera",
               query = "SELECT m FROM CarreraMateria m WHERE m.idCarrera = :carrera")
public class CarreraMateria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarreraMateria;
    
    @ManyToOne
    @JoinColumn
    private Carrera idCarrera;
    
    @ManyToOne
    @JoinColumn
    private Materia idMateria;

    public Integer getIdCarreraMateria() {
        return idCarreraMateria;
    }

    public void setIdCarreraMateria(Integer idCarreraMateria) {
        this.idCarreraMateria = idCarreraMateria;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    
}
