package com.ipn.mx.siipg.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ErrorBean {

    public void throwError(){
        throw new RuntimeException("throwing new error");
    }
}
