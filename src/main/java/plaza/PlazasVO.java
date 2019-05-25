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
    private boolean estadoPlaza;
    private double tarifa;
    private static int contador=45; //Usamos este contador para instanciar la clave primaria, es decir, el número de plaza

    public PlazasVO(String tipoPlaza) { //Al constructor no debemos decirle el numPlaza, lo debe asignar él
        //por lo tanto solo le pasamos el tipo de plaza que es
        this.numPlaza = contador;
        this.tipoPlaza = tipoPlaza;
        this.estadoPlaza = true;
        //Aquí hay que poner ifs dependiendo del tipoPlaza para asignarle un valor a la tarifa
        if (tipoPlaza.equalsIgnoreCase("Turismo")) {
            this.tarifa = 0.12;
        }
        if (tipoPlaza.equalsIgnoreCase("Motocicletas")) {
            this.tarifa = 0.08;
        }
        if (tipoPlaza.equalsIgnoreCase("Caravana")) {
            this.tarifa = 0.45;
        }
        contador--;
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

    public boolean isEstadoPlaza() {
        return estadoPlaza;
    }

    public void setEstadoPlaza(boolean estadoPlaza) {
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
