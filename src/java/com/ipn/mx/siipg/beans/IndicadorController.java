/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.IndicadorDao;
import com.ipn.mx.siipg.dao.util.JsfUtil;
import com.ipn.mx.siipg.impl.IndicadorDaoImpl;
import com.ipn.mx.siipg.modelo.Ejetematico;
import com.ipn.mx.siipg.modelo.Indicador;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named
@SessionScoped
public class IndicadorController implements Serializable {

    ServletContext serlvletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

    // Controlan el viewList
    private Indicador current;
    private List<Indicador> items;
    private String selectedEje;

    //Subida de archivos 
    private final String pdfPath = serlvletContext.getRealPath("/resources/catalogos/indicadores/");
    private UploadedFile file;
    private String fileName;
    private String editPdfPath = "0";

    //Deshabilita el botón de crear hasta que el archivo se haya subidó correctamente 
    private boolean enableToCreate;

    public IndicadorController() {
        System.out.println(pdfPath);
        enableToCreate = true;

        //Setea la dirección del servidor donde serán almacenados los pdfs asociados a los indicadores.
        // this.pdfPath = ResourceBundle.getBundle("Bundle").getString("system.savePath");
        // Llena la lista de indicadores sin ningún filtro por eje
//        IndicadorDao indicadorDao = new IndicadorDaoImpl();
//        this.items = indicadorDao.loadIndicadoresList();
    }

    //getters and setters
    public Indicador getCurrent() {
        return current;
    }

    public void setCurrent(Indicador current) {
        this.current = current;
    }

    public List<Indicador> getItems() {
        IndicadorDao indicadorDao = new IndicadorDaoImpl();
        return items = indicadorDao.loadIndicadoresList();
    }

    public void setItems(List<Indicador> items) {
        this.items = items;
    }

    public String getSelectedEje() {
        return selectedEje;
    }

    public void setSelectedEje(String selectedEje) {
        this.selectedEje = selectedEje;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isEnableToCreate() {
        return enableToCreate;
    }

    public void setEnableToCreate(boolean enableToCreate) {
        this.enableToCreate = enableToCreate;
    }

    public String getEditPdfPath() {
        return editPdfPath;
    }

    public void setEditPdfPath(String editPdfPath) {
        this.editPdfPath = editPdfPath;
    }

    //Métodos del bean
    public void updateItems() {
        IndicadorDao indicadorDao = new IndicadorDaoImpl();
        if (Integer.valueOf(selectedEje) == -1) {
            items = indicadorDao.loadIndicadoresList();
        } else {
            Ejetematico ejeTematico = new Ejetematico();
            ejeTematico.setId(Integer.valueOf(selectedEje));
            items = indicadorDao.loadIndicadoresByEje(ejeTematico);
        }
    }

    public void fileUploadListener(FileUploadEvent event) {
        this.file = event.getFile();
        try {
            copyFile(file.getFileName(), file.getInputstream());
            fileName = file.getFileName();
            enableToCreate(false, "IndicadorCreateForm:createButtonView");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("indicador.fileUploaded"));
        } catch (IOException ex) {
            printError(ex);
        }
    }

    public void copyFile(String fileName, InputStream inputStream) throws IOException {

        OutputStream out = new FileOutputStream(new File(pdfPath + "\\" + fileName));
        int read = 0;
        byte[] bytes = new byte[1024];

        while ((read = inputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        // Cerrando recursos y limpiando el buffer.
        inputStream.close();
        out.flush();
        out.close();
    }

    public void enableToCreate(Boolean disabled, String idComponent) {
        enableToCreate = disabled;
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(idComponent);
    }

    public void prepareCreate() {
        current = new Indicador();
    }

    public void createItem() {
        try {
            IndicadorDao indicadorDao = new IndicadorDaoImpl();
            Ejetematico ejeTematico = new Ejetematico();
            ejeTematico.setId(Integer.valueOf(selectedEje));
            current.setEjetematico(ejeTematico);
            current.setRutaPdf(fileName);
            indicadorDao.newIndicador(current);
            //updateItems();
            enableToCreate = true;
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("indicador.create"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void deleteItem() {
        try {
            IndicadorDao indicadorDao = new IndicadorDaoImpl();
            indicadorDao.deleteIndicador(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("indicador.delete"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void updateItem() {
        try {
            IndicadorDao indicadorDao = new IndicadorDaoImpl();
            Ejetematico ejeTematico = new Ejetematico();
            ejeTematico.setId(Integer.valueOf(selectedEje));
            current.setEjetematico(ejeTematico);

            if (editPdfPath.equals("1")) {
                current.setRutaPdf(fileName);
                editPdfPath = "0";
            }

            indicadorDao.updateIndicador(current);
            //System.out.println(editPdfPath);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString("indicador.update"));
        } catch (Exception ex) {
            printError(ex);
        }
    }

    public void fixSelectOneMenu() {
        selectedEje = current.getEjetematico().getId().toString();
    }

    public void printError(Exception ex) {
        JsfUtil.addErrorMessage(ResourceBundle.getBundle("Bundle").getString("system.error"));
        System.out.println(ex.toString());
    }
}
