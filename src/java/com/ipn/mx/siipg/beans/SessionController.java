package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.UsuarioDao;
import com.ipn.mx.siipg.impl.UsuarioDaoImpl;
import com.ipn.mx.siipg.modelo.Usuario;
import com.ipn.mx.siipg.modelo.UsuarioId;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.dao.util.MailService;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import java.io.IOException;
import java.util.Random;
import java.util.ResourceBundle;
import javax.faces.context.ExternalContext;
import javax.mail.MessagingException;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped

public class SessionController implements Serializable {

    private Usuario usuario = new Usuario();
    private UsuarioId usuarioId = new UsuarioId();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioId getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuarioId usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void accessCheck() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuario.setId(this.usuarioId);
        try {
            Usuario user = usuarioDao.checkUser(this.usuario);

            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

            } else {
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("msg4Error"));
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("msg5Error"));
                // System.out.println("Error el usuario no existe");

            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public void signOff() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (Exception ex) {
            System.out.println(ex.toString()
            );
        }

    }

    public String getNombreFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario user = (Usuario) context.getSessionMap().get("usuario");
        return user.getNombre() + " " + user.getApellidoPaterno() + " " + user.getApellidoMaterno();
    }

    public String getRfcFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario user = (Usuario) context.getSessionMap().get("usuario");
        return user.getId().getRfc();
    }

    public String getUnidadFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario user = (Usuario) context.getSessionMap().get("usuario");
        return user.getUnidadresponsable().getNombre();
    }

    public Unidadresponsable getUnidadObjectFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario user = (Usuario) context.getSessionMap().get("usuario");
        return user.getUnidadresponsable();
    }

    public String getRolFromSession() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Usuario user = (Usuario) context.getSessionMap().get("usuario");
        return user.getRol().getRol();
    }

    public void restaurarContrasena() {
        String rfc = usuarioId.getRfc();
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        MailService mailService = new MailService();
        if (rfc != null && rfc.trim().length() != 0) {
            Usuario tempUser = usuarioDao.checkRfc(rfc);
            String newPassword = "DummyPassword" + new Random().nextInt(99);
            if (tempUser != null) {
                tempUser.setPassword(newPassword);
                usuarioDao.updateUser(tempUser);
                try {
                    mailService.sendRecoveryPasswordEmail(tempUser);
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('recoverPasswordWI').hide();");
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("mail.recovery"));
                } catch (MessagingException messagingException) {
                    JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("mail.error"));
                }
            }
        }
    }

    public void actualizarPerfil() {
        Usuario sessionUser = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        String password = sessionUser.getPassword() != null ? sessionUser.getPassword() : this.usuario.getPassword();
        sessionUser.setPassword(password);
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        usuarioDao.updateUser(sessionUser);
        this.usuario = sessionUser;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editProfileWI').hide();");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", this.usuarioId);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("profile.update"));
    }
}
