/*
 * This code was developed by Edmundo Carrillo on java technologies.
 * Contact: edmundodev@gmail.com
 * Hope you'll find it useful.
 */
package com.ipn.mx.siipg.beans;

import com.ipn.mx.siipg.dao.IndicadorDao;
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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@Named
@SessionScoped
public class IndicadorController implements Serializable {

    private Indicador current;
    private List<Indicador> items;
    private String selectedEje;
    private final String pdfPath = "C:\\indicadoresPDFs\\";

    public IndicadorController() {

        IndicadorDao indicadorDao = new IndicadorDaoImpl();
        this.items = indicadorDao.loadIndicadoresList();
//        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
//        System.out.println(sc.getRealPath("/"));
    }

    public Indicador getCurrent() {
        return current;
    }

    public void setCurrent(Indicador current) {
        this.current = current;
    }

    public List<Indicador> getItems() {
        return items;
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

    public void copyFile(String nombreDelArchivo, InputStream in) {
//        try (
//            OutputStream out = new FileOutputStream(new File(rutaAlmacenarImagen + "/" + nombreDelArchivo))) {
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            while ((read = in.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//            in.close();
//            out.flush();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//
//        }

    }

}
