package Controlador;

import entidades.Materia;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import servicioGeneral.ServicioMateria;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ControladoMateria implements Serializable{

    @EJB
    private ServicioMateria sm;
    
    private Materia ma;
    
    private String nombreMateria;
    
    private List<Materia> listaMateria;

    public ControladoMateria() {
        ma = new Materia();
    }

    public void registrarMateria(){
        int c = (int) (Math.random() * 10000) + 1;
        ma.setIdMateria(c);
        ma.setNombreMateria(nombreMateria);
        sm.guardar(ma);
    
    }
    
    @PostConstruct
    public void inicio(){
        listar();
    }
    
    private void listar(){
        listaMateria = sm.listar(Materia.class);
    }
    
    
    public Materia getMa() {
        return ma;
    }

    public void setMa(Materia ma) {
        this.ma = ma;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public List<Materia> getListaMateria() {
        return listaMateria;
    }

    public void setListaMateria(List<Materia> listaMateria) {
        this.listaMateria = listaMateria;
    }
 
}
