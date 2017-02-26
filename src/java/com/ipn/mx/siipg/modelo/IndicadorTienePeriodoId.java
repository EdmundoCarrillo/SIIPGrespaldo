package com.ipn.mx.siipg.modelo;
// Generated 11/02/2017 08:23:27 PM by Hibernate Tools 4.3.1



/**
 * IndicadorTienePeriodoId generated by hbm2java
 */
public class IndicadorTienePeriodoId  implements java.io.Serializable {


     private int indicadorId;
     private int periodoId;

    public IndicadorTienePeriodoId() {
    }

    public IndicadorTienePeriodoId(int indicadorId, int periodoId) {
       this.indicadorId = indicadorId;
       this.periodoId = periodoId;
    }
   
    public int getIndicadorId() {
        return this.indicadorId;
    }
    
    public void setIndicadorId(int indicadorId) {
        this.indicadorId = indicadorId;
    }
    public int getPeriodoId() {
        return this.periodoId;
    }
    
    public void setPeriodoId(int periodoId) {
        this.periodoId = periodoId;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof IndicadorTienePeriodoId) ) return false;
		 IndicadorTienePeriodoId castOther = ( IndicadorTienePeriodoId ) other; 
         
		 return (this.getIndicadorId()==castOther.getIndicadorId())
 && (this.getPeriodoId()==castOther.getPeriodoId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIndicadorId();
         result = 37 * result + this.getPeriodoId();
         return result;
   }   


}

