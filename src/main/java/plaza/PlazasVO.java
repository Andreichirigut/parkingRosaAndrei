/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plaza;

/**
 *
 * @author Chiri
 */
public class PlazasVO {
    private int numPlaza;
    private String tipoPlaza;
    private int estadoPlaza;
    private double tarifa;

    public PlazasVO(int numPlaza, String tipoPlaza, int estadoPlaza, double tarifa) {
        this.numPlaza = numPlaza;
        this.tipoPlaza = tipoPlaza;
        this.estadoPlaza = estadoPlaza;
        this.tarifa = tarifa;
    }

    public PlazasVO() {
    }

    public int getNumPlaza() {
        return numPlaza;
    }

    public void setNumPlaza(int numPlaza) {
        this.numPlaza = numPlaza;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public void setTipoPlaza(String tipoPlaza) {
        this.tipoPlaza = tipoPlaza;
    }

    public int isEstadoPlaza() {
        return estadoPlaza;
    }

    public void setEstadoPlaza(int estadoPlaza) {
        this.estadoPlaza = estadoPlaza;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "PlazasVO{" + "numPlaza=" + numPlaza + ", tipoPlaza=" + tipoPlaza + ", estadoPlaza=" + estadoPlaza + ", tarifa=" + tarifa + '}';
    }

    
    
    
    
    

   
    
    
}
