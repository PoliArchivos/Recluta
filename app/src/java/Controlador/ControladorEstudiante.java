package Controlador;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.Facultad;
import entidades.Roles;
import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import servicioGeneral.ServicioCarrera;
import servicioGeneral.ServicioEstudiante;
import servicioGeneral.ServicioRoles;
import servicioGeneral.ServicioUsuario;

/**
 *
 * @author USER
 */
@ManagedBean
@ViewScoped
public class ControladorEstudiante implements Serializable {

    @EJB
    private ServicioUsuario sa;

    @EJB
    private ServicioRoles sr;

    @EJB
    private ServicioEstudiante se;

    @EJB
    private ServicioCarrera sc;

    private Estudiante est;

    private Usuario usu;

    private Roles rol;

    private Carrera car;

    private List<Estudiante> listaEst;
    private List<Usuario> listaUsu;
    private int id;
    private int idu;

    /**
     * Creates a new instance of ControladorCarrera
     */
    public ControladorEstudiante() {
        usu = new Usuario();
        rol = new Roles();
        est = new Estudiante();
        car = new Carrera();
    }

    public void registrar() {

        Carrera c = sc.consultarXId(Carrera.class, car.getIdCarrera());
        if (c != null) {
            usu.setContrasena(est.getIdentificacion());
            usu.setUsuario(est.getEmail());
            rol.setNombreRol("Estudiante");
            rol = sr.rolesXNombre(rol);
            usu.setRol(rol);

            idu = (int) (Math.random() * 10000) + 1;
            usu.setIdUsuario(idu);
            Usuario usu3 = sa.consultarUsuario(usu);
            if (usu3 != null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario ya existe"));
            } else {
                sa.guardar(usu);
                Usuario usu2 = sa.consultarUsuario(usu);

                est.setUsuario(usu2);
                id = (int) (Math.random() * 10000) + 1;
                est.setId(id);
                est.setCarrera(c);
                se.guardar(est);
            }
        }
    }

    @PostConstruct
    public void inicio() {
        listar();
    }

    private void listar() {
        listaEst = se.listar(Estudiante.class);
        listaUsu = sa.listar(Usuario.class);
    }

    private void gestion_id() {
        id = listaEst.size();
        idu = listaUsu.size();
    }

    public Estudiante getEst() {
        return est;
    }

    public void setEst(Estudiante est) {
        this.est = est;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Estudiante> getListaEst() {
        return listaEst;
    }

    public void setListaEst(List<Estudiante> listaEst) {
        this.listaEst = listaEst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Usuario> getListaUsu() {
        return listaUsu;
    }

    public void setListaUsu(List<Usuario> listaUsu) {
        this.listaUsu = listaUsu;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public Carrera getCar() {
        return car;
    }

    public void setCar(Carrera car) {
        this.car = car;
    }

}
