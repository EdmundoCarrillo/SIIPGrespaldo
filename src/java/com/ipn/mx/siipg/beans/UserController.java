
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.AreaPolitecnicaDao;
import com.ipn.mx.siipg.dao.RolDao;
import com.ipn.mx.siipg.dao.UsuarioDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.AreaPolitecnicaDaoImpl;
import com.ipn.mx.siipg.impl.RolDaoImpl;
import com.ipn.mx.siipg.impl.UsuarioDaoImpl;
import com.ipn.mx.siipg.modelo.Areapolitecnica;
import com.ipn.mx.siipg.modelo.Rol;
import com.ipn.mx.siipg.modelo.Unidadresponsable;
import com.ipn.mx.siipg.modelo.Usuario;
import com.ipn.mx.siipg.modelo.UsuarioId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@Named
@SessionScoped
public class UserController implements Serializable {

    private Usuario current;
    private List<Usuario> items;
    private Usuario selected;
    private String selectedRol;
    private String selectedUnidad;
    private String selectedArea;
    private String rfcmsg;
    private Boolean enableCreate;
    private List<Rol> listr = null;
    private List<Unidadresponsable> listu = null;
    private List<Areapolitecnica> listArea = null;

    public void setRfcmsg(String rfcmsg) {
        this.rfcmsg = rfcmsg;
    }

    public String getRfcmsg() {
        return rfcmsg;
    }

    public void setEnableCreate(Boolean enableCreate) {
        this.enableCreate = enableCreate;
    }

    public Boolean getEnableCreate() {
        return enableCreate;
    }

    public void setListr(List<Rol> listr) {
        this.listr = listr;
    }

    public List<Rol> getListr() {
        return listr;
    }

    public void setListArea(List<Areapolitecnica> listArea) {
        this.listArea = listArea;
    }

    public List<Areapolitecnica> getListArea() {
        return listArea;
    }

    public void setListu(List<Unidadresponsable> listu) {
        this.listu = listu;
    }

    public List<Unidadresponsable> getListu() {
        return listu;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public UserController() {
        enableCreate = false;

        if (listr == null) {
            RolDao rolDao = new RolDaoImpl();
            listr = rolDao.loadRoles();
        }

        if (listArea == null) {
            AreaPolitecnicaDao areaPolitecnica = new AreaPolitecnicaDaoImpl();
            listArea = areaPolitecnica.loadAreas();
        }
    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public void Editable() {
        Unidadresponsable UnidadR = current.getUnidadresponsable();
        Areapolitecnica areaP = UnidadR.getAreapolitecnica();
        selectedUnidad = UnidadR.getId().toString();
        selectedArea = areaP.getId().toString();
        selectedRol = current.getRol().getId().toString();
        generateUnidades(areaP);
    }

    public void generateUnidades(Areapolitecnica areaP) {
        Object[] unidades = areaP.getUnidadresponsables().toArray();
        listu = new ArrayList<>(unidades.length);
        for (Object unidad : unidades) {
            Unidadresponsable temp = (Unidadresponsable) unidad;
            listu.add(temp);
        }
    }

    public void create() {
        rfcmsg = " ";
        selected = new Usuario(new UsuarioId(), null, null, " ", 0, "", "", "", "", "");
        generateUnidades(listArea.get(0));
    }

    public void updateunidades() {
        selectedUnidad = null;
        generateUnidades(listArea.get(Integer.parseInt(selectedArea) - 1));
    }

    public List<Usuario> getItems() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        return items = usuarioDao.loadUsers();
    }

    public void setItems(List<Usuario> items) {
        this.items = items;
    }

    public String prepareCreate() {
        current = new Usuario();
        return "Create";
    }

    public String prepareEdit(Usuario user) {
        current = user;
        return "Edit";
    }

    public String prepareEdit2() {

        return "Edit";
    }

    public void verifyRfc() {
        String rfc = selected.getId().getRfc();
        if (rfc != null && rfc.trim().length() != 0) {
            UsuarioDao temp = new UsuarioDaoImpl();
            if (temp.checkRfc(rfc) == null) {
                rfcmsg = "   Rfc valido";
                if (enableCreate) {
                    enableCreate = false;
                }
            } else {
                rfcmsg = "   Rfc existente";
                enableCreate = true;
            }
        }
    }

    public void createItem() {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Unidadresponsable unidad = new Unidadresponsable();
        Rol rol = new Rol();
        unidad.setId(Integer.valueOf(selectedUnidad));
        rol.setId(Integer.valueOf(selectedRol));
        selected.setUnidadresponsable(unidad);
        selected.setRol(rol);
        try {
            usuarioDao.newUser(selected);
        } catch (Exception e) {
            System.out.println(e);
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('createUserWI').hide();");
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("Usuarios.create"));
        selected = new Usuario(new UsuarioId(), null, null, " ", 0, "", "", "", "", "");

    }

    public void editItem() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        Unidadresponsable unidad = new Unidadresponsable();
        Rol rol = new Rol();
        unidad.setId(Integer.valueOf(selectedUnidad));
        rol.setId(Integer.valueOf(selectedRol));
        current.setUnidadresponsable(unidad);
        current.setRol(rol);
        usuarioDao.updateUser(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("Usuarios.update"));
    }

    public void deleteItem() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        usuarioDao.deleteUser(current);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("Usuarios.delete"));
        current = new Usuario();
    }

    public String getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(String selectedRol) {
        this.selectedRol = selectedRol;
    }

    public String getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(String selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }

    public void setSelectedArea(String selectedArea) {
        this.selectedArea = selectedArea;
    }

    public String getSelectedArea() {
        return selectedArea;
    }
}
