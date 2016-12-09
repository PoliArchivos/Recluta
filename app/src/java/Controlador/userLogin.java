/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import entidades.Usuario;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import servicioGeneral.ServicioUsuario;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class userLogin implements Serializable {

    @EJB
    private ServicioUsuario su;

    private Usuario us;

    public userLogin() {
        us = new Usuario();
    }

    public void login() {
        try {
            Usuario u = su.loginUsuario(us);
            if (u != null) {
                if (u.getRol().getIdRol()== 2) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", u);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Administracion.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogin", u);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("PrincipalEstudiante.xhtml");
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("usuario y contraseña no coinciden"));
            }
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("error al redireccionar"));
        }
    }
    
    public void cerrarSesion() throws IOException{
        if(us != null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogin");
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }
    
}
